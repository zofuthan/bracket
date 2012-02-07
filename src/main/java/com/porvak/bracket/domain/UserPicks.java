package com.porvak.bracket.domain;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

public class UserPicks extends AbstractBracket{
    private String userId;
    private String tournamentId;
    private String poolId;
    private List<UserPick> picks;
    private Map<SearchKey, UserPick> indexedPicks;

    public UserPicks() {
        picks = Lists.newArrayList();
    }
    
    public void addUserPick(UserPick pick){
        picks.add(pick);
        if(!arePicksIndexed()){
            createIndexedPicks();
        }
        indexedPicks.put(new SearchKey(pick.getRegionId(), pick.getGameId()), pick);
    }
    
    private boolean arePicksIndexed(){
        return indexedPicks != null && indexedPicks.size() != 0;
    } 
    
    private void createIndexedPicks(){
        indexedPicks = Maps.uniqueIndex(picks, new Function<UserPick, SearchKey>() {
            public SearchKey apply(@Nullable UserPick input) {
                if(input == null){
                    return null;
                }
                return new SearchKey(input.getRegionId(), input.getGameId());
            }
        });
    }
    
    public UserPick getByRegionIdAndGameId(int regionId, int gameId){
        if(!arePicksIndexed()){
            createIndexedPicks();
        }
        return indexedPicks.get(new SearchKey(regionId, gameId));
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(String tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }
    
    private class SearchKey {
        private int regionId;
        private int gameId;

        public SearchKey(int regionId, int gameId) {
            this.regionId = regionId;
            this.gameId = gameId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof SearchKey){
                final SearchKey searchKey = (SearchKey) o;
                return Objects.equal(gameId, searchKey.gameId)
                        && Objects.equal(regionId, searchKey.regionId);
            }
            
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(regionId, gameId);
        }
    }
}