package ventus.rggwheel.model;

public enum PrizeEnum {
    ADD_GAME("Extra Game", "Player flips a coin. Losing adds +1 game to the current platform.", "+ Game"),
    TREASURE("Treasure", "The last donator chooses next game on the current platform.", "Treasure"),
    LUCKY_SUB("Lucky Sub", "The last subscriber/member chooses next game on the current platform.", "Lucky Sub"),
    CHAT_PICK("Chat of Five", "Viewers can vote for 1 of the 5 games displayed by the generator.", "Chat o'Five"),
    JUAN_OF_FIVE("Juan of Five", "The player rolls next game in the generator and can pick one from the 5 games shown on screen.", "Juan o'Five"),
    SPINNER_OF_FIVE("Spinner of Five", "Next game will be selected by the supporter among the 5 games displayed by the generator on the current platform.", "Spinner o'Five"),
    SPIN_AGAIN("Spin again", "Player spins the wheel one more time.", "Spin again"),
    TIER_PLUS("Tier plus", "Donation and Sub incentives amounts increase by 5.", "Tier +"),
    AUCTION_GAME("Auction", "A 3-minute timer will be started. The person who donated the largest single donation chooses next game on the current platform.", "Auction"),
    COIN_FLIP("Coin flip", "Player can bet any amount of coins on a coin flip! Good luck!", "Coin flip"),
    BAG("Mystery Bag", "A random number of coins is added/substracted from -30 to +50", "Mystery Bag"),
    HOT_STREAK("Hot Streak", "Coins are awarded to the player for his current completed games streak at +5 coins per game. Use !rgg for more info", "Hot Streak"),
    CURSE("Curse", "What a horrible night to have a “cursed wheel”, the player spins again the wheel but with spooky effects. Use !rgg for spooky effects table", "Curse"),
    RESET("Reset", "Subs and Donation incentives for a given platform resets (this includes current donations and subs points). Use !rgg for more info", "Reset");

    final String name;
    final String description;
    final String label;

    PrizeEnum(String name, String description, String label) {
        this.name = name;
        this.description = description;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLabel() {
        return label;
    }
}
