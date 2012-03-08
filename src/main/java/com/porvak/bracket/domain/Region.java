package com.porvak.bracket.domain;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.*;

public class Region extends AbstractBracket{
    @JsonProperty("regionId")
    @Field("regionId")
    private int regionId;

    private String name;

    @JsonProperty
    private List<Round> rounds;

    @Transient
    private Map<Integer, Round> indexedRounds;

    public Region(){
        rounds = Lists.newLinkedList();
        indexedRounds = Maps.newHashMap();
    }
    
    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Round> getRounds(){
        return ImmutableList.copyOf(rounds);
    }

    public Round getRoundById(int id){
        if (indexedRounds == null || indexedRounds.size() == 0) {
            indexedRounds = Maps.uniqueIndex(rounds, new Function<Round, Integer>() {
                public Integer apply(@Nullable Round input) {
                    if(input == null || input.getRoundId() == 0){
                        return null;
                    }
                    return input.getRoundId();
                }
            });
        }
        return indexedRounds.get(id);
    }

    public void addRound(Round round) {
        round = checkNotNull(round, "Round cannot be null");
        checkArgument(round.getRoundId() >= 1,"Round ID must be greater than or equal to 1: Received [%s]", round.getRoundId());
        rounds.add(round);
        indexedRounds.put(round.getRoundId(), round);
    }
}
