'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;
var userID = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];


function connect(event) {
    username = document.querySelector('#name').value.trim();
    userID = document.querySelector('#userID').value.trim();

    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat/addUser",
        {},
        JSON.stringify({sender: username, userID: userID, messageType: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            userID: userID,
            sender: username,
            content: messageInput.value,
            messageType: 'CHAT'
        };
        stompClient.send("/app/chat/sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

    function onMessageReceived(payload) {

        var messages = JSON.parse(payload.body);

        if(Array.isArray(messages)) {

            for (var i = 0; i < messages.length; i++) {
                renderMessage(messages[i]);
            }

        } else {
            renderMessage(messages);
        }

    }

    function renderMessage(messages) {
        var messageElement = document.createElement('li');
            if(messages.messageType === 'JOIN') {
                messageElement.classList.add('event-message');
                messages.content = messages.sender + ' joined!';
            } else if (messages.messageType === 'LEAVE') {
                messageElement.classList.add('event-message');
                messages.content = messages.sender + ' left!';
            } else {
                messageElement.classList.add('chat-message');

                var avatarElement = document.createElement('i');
                var avatarText = document.createTextNode(messages.sender);
                avatarElement.appendChild(avatarText);
                avatarElement.style['background-color'] = getAvatarColor(messages.sender[0]);

                messageElement.appendChild(avatarElement);

                var usernameElement = document.createElement('span');
                var usernameText = document.createTextNode(messages.sender);
                usernameElement.appendChild(usernameText);
                messageElement.appendChild(usernameElement);
            }

                var textElement = document.createElement('p');
                var messageText = document.createTextNode(messages.content);
                textElement.appendChild(messageText);

                messageElement.appendChild(textElement);

                messageArea.appendChild(messageElement);
                messageArea.scrollTop = messageArea.scrollHeight;

                var timeElement = document.createElement('p');
                var messageDate = document.createTextNode(messages.createdDateTime);
                timeElement.appendChild(messageDate);

                messageElement.appendChild(timeElement);

                messageArea.appendChild(messageElement);
                messageArea.scrollTop = messageArea.scrollHeight;
    }


    function getAvatarColor(messageSender) {
        var hash = 0;
        for (var i = 0; i < messageSender.length; i++) {
            hash = 31 * hash + messageSender.charCodeAt(i);
        }
        var index = Math.abs(hash % colors.length);
        return colors[index];
    }

    usernameForm.addEventListener('submit', connect, true)
    messageForm.addEventListener('submit', sendMessage, true)