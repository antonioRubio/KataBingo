package main;

public class Bingo {
	private Card[] cards;
	private NumberGenerator generator;
	private Card lineAwardCard;
	private Card bingoAwardCard;
	
	public Bingo(int numCards) throws GapException {
		generator = new NumberGenerator();
		cards = new Card[numCards];
		for (int i = 0; i < numCards; i++)
			cards[i] = CardGenerator.getCard();
	}
	
	public void play() {
		while (generator.hasNext() && !isBingoAwarded()) {
			boolean lastLineAwarded = isLineAwarded();
			int nextNumber = generator.getNextNumber();
			markNumberInAllCards(nextNumber);
			if (isLineAwarded() && !lastLineAwarded) {
				System.out.println("LINEA:");
				System.out.println(lineAwardCard);
			}
			
		}
		System.out.println("BINGO");
		System.out.println(bingoAwardCard);
	}

	private void markNumberInAllCards(int nextNumber) {
		for (int i = 0; i < cards.length; i++) {
			Card card = cards[i];
			card.mark(nextNumber);
			if (!isLineAwarded() && card.hasLineAward())
				lineAwardCard = card;
			if (!isBingoAwarded() && card.hasBingoAward())
				bingoAwardCard = card;
		}
			
	}

	private boolean isLineAwarded() {
		return lineAwardCard != null;
	}
	
	private boolean isBingoAwarded() {
		return bingoAwardCard != null;
	}
	
}
