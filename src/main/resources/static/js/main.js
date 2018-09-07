'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var roomForm = document.querySelector('#roomForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var clickUserNameButton = null;
var clickInviteButton = null;


var stompClient = null;
var username = null;
var senderID = null;
var receiverName = null;
var receiverID = null;
var roomValue = null;
var roomID = null;
var roomName = null;
var createdByUserID = null;
var messagePrefix = '';

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = document.querySelector('#name').value.trim();
    senderID = document.querySelector('#userID').value.trim();
    roomValue = document.querySelector('#room').value.split('-');
    roomID = roomValue[0];
    createdByUserID = roomValue[1];

    roomName = document.querySelector('#room').options[ document.querySelector('#room').selectedIndex ].text;

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

    var chatHeader = document.getElementsByClassName('chat-header')[0];
    var roomNameHeader = document.createElement('h3');
    var roomNameText = document.createTextNode('Chat: ' + roomName);
    roomNameHeader.appendChild(roomNameText);
    chatHeader.appendChild(roomNameHeader);
    if(roomName != 'all' && createdByUserID === senderID) {

        var inviteButton = document.createElement('button');
        inviteButton.classList.add('btn');
        inviteButton.classList.add('btn-success');
        inviteButton.setAttribute("id", "inviteButton" + senderID);
        var inviteButtonText = document.createTextNode('Invite users...');
        inviteButton.appendChild(inviteButtonText);
        chatHeader.appendChild(inviteButton);
    }

    if(roomName != 'all' && createdByUserID != senderID) {

            var leaveButton = document.createElement('button');
            leaveButton.classList.add('btn');
            leaveButton.classList.add('btn-danger');
            leaveButton.setAttribute("id", "leaveButton" + senderID);
            var leaveButtonText = document.createTextNode('Leave this chat room...');
            leaveButton.appendChild(leaveButtonText);
            chatHeader.appendChild(leaveButton);
        }



            // Subscribe to the Public Topic
                stompClient.subscribe('/topic/public/{' + senderID + '}/{' + roomID + '}', onMessageReceived);

                // Tell your username to the server
                stompClient.send("/app/chat/" + senderID + "/addUser/" + roomID + "",
                    {},
                    JSON.stringify({senderName: username, 'senderID': senderID, 'receiverName': (receiverName ? receiverName : username), 'receiverID': (receiverID ? receiverID : senderID), 'chatRoom': {'roomID': roomID, 'roomName': roomName}, messageType: 'JOIN'})
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
                        'senderID': senderID,
                        'chatRoom': {'roomID':  roomID, 'roomName': roomName},
                        senderName: username,
                        'receiverName': (receiverName ? receiverName : username),
                        'receiverID': (receiverID ? receiverID : senderID),
                        content: messageInput.value,
                        messageType: 'CHAT'
                    };

            stompClient.send("/app/chat/" + senderID + "/sendMessage/" + roomID + "", {}, JSON.stringify(chatMessage));
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

        clickUserNameButton = document.querySelectorAll('[id^="userNameButton"]');
        onClickUserNameButton();

        clickInviteButton = document.querySelectorAll('[id^="inviteButton"]');
        onClickInviteButton();

    }

    function renderMessage(messages) {

        if(messages.chatRoom.roomID === parseInt(roomID, 10)) {

            var messageElement = document.createElement('li');
                        if(messages.messageType === 'JOIN') {
                            messageElement.classList.add('event-message');
                            messages.content = messages.senderName + ' joined!';
                        } else if (messages.messageType === 'LEAVE') {
                            messageElement.classList.add('event-message');
                            messages.content = messages.senderName + ' left!';
                        } else {
                            messageElement.classList.add('chat-message');

                            var avatarElement = document.createElement('i');
                            var avatarText = document.createTextNode(messages.senderName);
                            avatarElement.appendChild(avatarText);
                            avatarElement.style['background-color'] = getAvatarColor(messages.senderName[0]);

                            messageElement.appendChild(avatarElement);

                            var usernameButtonElement = document.createElement('button');
                            usernameButtonElement.classList.add('btn');
                            usernameButtonElement.classList.add('btn-light');
                            usernameButtonElement.setAttribute("id", "userNameButton-" + messages.createdDateTime);

                            var usernameElement = document.createElement('span');
                            usernameElement.classList.add('usernameElement');
                            var usernameText = document.createTextNode(messages.senderName);
                            usernameElement.appendChild(usernameText);
                            usernameButtonElement.appendChild(usernameElement);

                            var userIDElement = document.createElement('span');
                            userIDElement.classList.add('userIDElement');
                            userIDElement.classList.add('hidden');
                            var userIDText = document.createTextNode(messages.senderID);
                            userIDElement.appendChild(userIDText);
                            usernameButtonElement.appendChild(userIDElement);

                            messageElement.appendChild(usernameButtonElement);
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

    function onClickUserNameButton(){

            for(var i = 0; i < clickUserNameButton.length; i++) {

                clickUserNameButton[i].addEventListener('click', function() {

                    var buttonID =this.id;

                    receiverName = document.getElementById(buttonID).getElementsByClassName('usernameElement')[0].innerText;
                    var receiverIDText = document.getElementById(buttonID).getElementsByClassName('userIDElement')[0].innerText;
                    receiverID = parseInt(receiverIDText, 0);

                    messagePrefix = 'To ' + receiverName + ': ';
                    document.getElementById('message').value = messagePrefix;

                }, false)
            }
    }

    function onClickInviteButton(){

                for(var i = 0; i < clickInviteButton.length; i++) {

                    clickInviteButton[i].addEventListener('click', function() {

                        $('#modelWindow').modal('show');

                    }, false)
                }
        }

