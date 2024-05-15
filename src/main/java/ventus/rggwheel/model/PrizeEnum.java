package ventus.rggwheel.model;

public enum PrizeEnum {
    ADD_GAME("+ Game", "Add one game on current platform.", "+ Game"),
    CHAT_CONSOLE("Chat Platform", "The next game will be a game from a platform chosen by chat, if it happens to be the last platform then the player has to spin the wheel again.", "Chat Platform"),
    RESET("Reset", "Subs and Donation incentives for a given platform resets (this includes current donations and subs points). Use !rgg for more info", "Reset"),
    MASTER("Master", "The next game is chosen by the player on the current platform.", "Master"),
    TREASURE("Treasure", "The last donator chooses the next game in the current platform.", "Treasure"),
    LUCKY_SUB("Lucky Sub", "The last subscriber chooses the next game on the current platform.", "Lucky Sub"),
    HELLO_KITTY("Cat in the Bag", "Next game will be chosen randomly from a list that includes all the games on each platform.", "Cat in the bag"),
    SPIN_AGAIN("Spin again", "The player spins the wheel one more time.", "Spin again"),
    HEADS_OR_TAILS("Heads or Tails", "Player makes a guess head or tail, then throws the coin. If he wins then one  game is subtracted, but if he loses then one game is added.", "Heads/Tails"),
    DOUBLE("Curse", "What a horrible night to have a “cursed wheel”, the player spins again the wheel but with spooky effects. Use !rgg for spooky effects table", "Curse"),
    CHAT_PICK("Chat of Five", "The player rolls “next game” in the generator and the audience can pick one from the 5 games shown on screen.", "Chat of Five"),
    JUAN_OF_FIVE("Juan of Five", "The player rolls next game in the generator and can pick one from the 5 games shown on screen.", "Juan of five"),
    FIVE_HINTS("Hints", "Item goes to inventory, when used audience spoilers will be allowed for the current game.", "Five hints"),
    POTION("Potion", "Item goes to inventory, when used the player can reroll the current game without any punishment.", "Potion"),
    MS_DOS("MS-DOS", "Next game is a MS-DOS game", "MS-DOS"),
    NOTHING("Ad Break", "Watch some ads and help your favorite streamer.", "Ad Break"),
    TIER_PLUS("Tier +", "The incentives will increase to the next level tier. If the sub incentive is currently at “10/15” then it will increase to “10/18”, the same will happen with the bits/donations.", "Tier +"),
    SELLOUT("- 50% Sellout", "The $$$ donations and incentive will be halved and it will stay that way across games and platforms. It will only be restored to original value after incentive is met.", "-50% Sellout"),
    PREVIOUS_NEXT("Previous/Next", "Next game will be rolled for the platform adjacent to the current one (decided by a coin toss, in case for first/last platform no coin toss is needed).", "Prev/Next"),
    SUBMISSION("Sub-Mission", " Next game will be rolled from the \"Subscriber List\" with the chance for the player to pick one of the five games shown on the roll machine.", "Sub-Mission");

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
