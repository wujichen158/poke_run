package com.qdu.pokerun.entity;

/**
 * 宝可梦类
 */
public class Pokemon {
    public String ability;
    public int abilitySlot;
    public int caughtBall;
    public String customTexture;
    public boolean doesLevel;
    public int dynamaxLevel;
    public Integer eggCycles;
    public Integer eggSteps;
    public int experience;
    public int friendship;
    public boolean gigantamaxFactor;
    public String growth;
    public Item heldItem;
    public boolean isShiny;
    public int level;
    public String mintNature;
    public String[] moveset;
    public String nature;
    public String nickname;
    public String originalTrainerName;
    public String originalTrainerUUID;
    public boolean pokerus;

    public String gender;
    public String form;
    public int ndex;

    public Pokemon(){
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public int getNdex() {
        return ndex;
    }

    public void setNdex(int ndex) {
        this.ndex = ndex;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getAbilitySlot() {
        return abilitySlot;
    }

    public void setAbilitySlot(int abilitySlot) {
        this.abilitySlot = abilitySlot;
    }

    public int getCaughtBall() {
        return caughtBall;
    }

    public void setCaughtBall(int caughtBall) {
        this.caughtBall = caughtBall;
    }

    public String getCustomTexture() {
        return customTexture;
    }

    public void setCustomTexture(String customTexture) {
        this.customTexture = customTexture;
    }

    public boolean isDoesLevel() {
        return doesLevel;
    }

    public void setDoesLevel(boolean doesLevel) {
        this.doesLevel = doesLevel;
    }

    public int getDynamaxLevel() {
        return dynamaxLevel;
    }

    public void setDynamaxLevel(int dynamaxLevel) {
        this.dynamaxLevel = dynamaxLevel;
    }

    public Integer getEggCycles() {
        return eggCycles;
    }

    public void setEggCycles(Integer eggCycles) {
        this.eggCycles = eggCycles;
    }

    public Integer getEggSteps() {
        return eggSteps;
    }

    public void setEggSteps(Integer eggSteps) {
        this.eggSteps = eggSteps;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getFriendship() {
        return friendship;
    }

    public void setFriendship(int friendship) {
        this.friendship = friendship;
    }

    public boolean isGigantamaxFactor() {
        return gigantamaxFactor;
    }

    public void setGigantamaxFactor(boolean gigantamaxFactor) {
        this.gigantamaxFactor = gigantamaxFactor;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }

    public Item getHeldItem() {
        return heldItem;
    }

    public void setHeldItem(Item heldItem) {
        this.heldItem = heldItem;
    }

    public boolean isShiny() {
        return isShiny;
    }

    public void setShiny(boolean shiny) {
        isShiny = shiny;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMintNature() {
        return mintNature;
    }

    public void setMintNature(String mintNature) {
        this.mintNature = mintNature;
    }

    public String[] getMoveset() {
        return moveset;
    }

    public void setMoveset(String[] moveset) {
        this.moveset = moveset;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOriginalTrainerName() {
        return originalTrainerName;
    }

    public void setOriginalTrainerName(String originalTrainerName) {
        this.originalTrainerName = originalTrainerName;
    }

    public String getOriginalTrainerUUID() {
        return originalTrainerUUID;
    }

    public void setOriginalTrainerUUID(String originalTrainerUUID) {
        this.originalTrainerUUID = originalTrainerUUID;
    }

    public boolean hasPokerus() {
        return pokerus;
    }

    public void setPokerus(boolean pokerus) {
        this.pokerus = pokerus;
    }
}
