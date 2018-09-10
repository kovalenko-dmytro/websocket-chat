package com.dkovalenko.websocketchat.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConstantsEnum {

    LAST_MESSAGES_INTERVAL_IN_HOURS(720L),
    DELETE_OLD_MESSAGES_INTERVAL_IN_HOURS(720L);

    private long value;
}
