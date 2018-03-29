package com.egencia.entity;

import com.egencia.enumeration.WorldCupGroup;
import com.egencia.enumeration.WorldCupType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Match {

    @Id
    String id;
    String homeTeam;
    String awayTeam;
    String stadium;
    String name;
    List<String> channels;
    boolean finished;
    Date startDate;
    WorldCupType type;
    WorldCupGroup group;
    String result;
}