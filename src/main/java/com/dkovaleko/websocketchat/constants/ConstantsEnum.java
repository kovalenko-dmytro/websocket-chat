package com.dkovaleko.websocketchat.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConstantsEnum {

    LAST_MESSAGES_INTERVAL(1L);

    private long value;
}
