import java.util.*;


public class CardDeck {

    private final Set<Card> deck;

    private final List<Card> deckOrder;


    public CardDeck() {
        deck = new HashSet<>();
        deckOrder = new ArrayList<>();
        initializeDeck();
    }


    private void initializeDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                Card card = new Card(rank, suit);
                deck.add(card);
                deckOrder.add(card);
            }
        }
    }


    public void shuffle() {
        Collections.shuffle(deckOrder);
    }


    public Card dealCard() {
        if (deckOrder.isEmpty()) {
            throw new IllegalStateException("Колода пуста! Нельзя сдать карту.");
        }
        Card card = deckOrder.remove(0);
        deck.remove(card); 
        return card;
    }


    public List<Card> dealCards(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Количество карт не может быть отрицательным.");
        }
        if (count > deckOrder.size()) {
            throw new IllegalStateException("В колоде недостаточно карт: запрошено " + count +
                    ", доступно " + deckOrder.size());
        }

        List<Card> dealtCards = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            dealtCards.add(dealCard());
        }
        return dealtCards;
    }


    public boolean returnCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Карта не может быть null.");
        }
        if (deck.contains(card)) {
            return false; 
        }
        deck.add(card);
        deckOrder.add(card);
        return true;
    }


    public int returnCards(List<Card> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("Список карт не может быть null.");
        }
        int addedCount = 0;
        for (Card card : cards) {
            if (returnCard(card)) {
                addedCount++;
            }
        }
        return addedCount;
    }


    public int size() {
        return deckOrder.size();
    }


    public boolean isEmpty() {
        return deckOrder.isEmpty();
    }

    public void clear() {
        deck.clear();
        deckOrder.clear();
    }
    @Override
    public String toString() {
        return "Колода: " + deckOrder;
    }

    public enum Suit {
        HEARTS("Черви"), DIAMONDS("Бубны"), CLUBS("Трефы"), SPADES("Пики");

        private final String name;

        Suit(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum Rank {
        TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
        EIGHT("8"), NINE("9"), TEN("10"), JACK("Валет"), QUEEN("Дама"),
        KING("Король"), ACE("Туз");

        private final String name;

        Rank(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }


    public static class Card {
        private final Rank rank;
        private final Suit suit;

        public Card(Rank rank, Suit suit) {
            this.rank = rank;
            this.suit = suit;
        }

        public Rank getRank() { return rank; }
        public Suit getSuit() { return suit; }

        @Override
        public String toString() {
            return rank + " " + suit;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Card)) return false;
            Card card = (Card) o;
            return rank == card.rank && suit == card.suit;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rank, suit);
        }
    }



    public static void main(String[] args) {
        CardDeck deck = new CardDeck();

        System.out.println("Колода создана. Размер: " + deck.size());
        System.out.println("Перемешиваем колоду...");
        deck.shuffle();

        System.out.println("Сдаём 5 карт:");
        List<Card> hand = deck.dealCards(5);
        for (Card card : hand) {
            System.out.println(" - " + card);
        }

        System.out.println("Осталось в колоде: " + deck.size());

        System.out.println("\nВозвращаем одну карту (дубликат):");
        Card duplicate = hand.get(0);
        boolean added = deck.returnCard(duplicate);
        System.out.println("Карта " + duplicate + " добавлена обратно: " + added);

        System.out.println("Возвращаем новую карту (не из колоды):");
        Card newCard = new Card(Rank.ACE, Suit.SPADES);
        boolean addedNew = deck.returnCard(newCard);
        System.out.println("Карта " + newCard + " добавлена: " + addedNew);

        System.out.println("Итоговый размер колоды: " + deck.size());
    }
}