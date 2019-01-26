package com.etroya.services;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by mada on 25.01.2019.
 */

public interface GreetingRepository {
    String getEnglishGreeting();

    String getSpanishGreeting();

    String getGermanGreeting();

    String getDutchGreeting();
}
