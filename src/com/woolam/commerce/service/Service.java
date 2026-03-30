package com.woolam.commerce.service;

import com.woolam.commerce.dto.ServiceData;
import com.woolam.commerce.dto.ServiceFlag;

public interface Service {
    ServiceFlag run(ServiceData data);
}
