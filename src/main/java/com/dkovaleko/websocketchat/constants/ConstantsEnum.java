package com.dkovaleko.websocketchat.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum ConstantsEnum {

    LAST_MESSAGES_INTERVAL_IN_HOURS(1L),
    DELETE_OLD_MESSAGES_INTERVAL_IN_HOURS(720L);

    private long value;
}
