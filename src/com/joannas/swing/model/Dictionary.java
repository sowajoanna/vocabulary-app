package com.joannas.swing.model;

import java.util.ArrayList;
import java.util.Collection;

public class Dictionary {
    private Collection<Item> items;
    private ArrayList<Item> itemsToDraw;
    private Item currentRandomItem;

    public Dictionary(Collection<Item> items) {
        this.items = items;
        fillItemsToDraw();
    }

    @Override
    public String toString() {
        return "Dictionary:" + items;
    }

    public String getRandomWord() {
        int randomIndex = (int) (Math.random() * itemsToDraw.size());
        currentRandomItem = itemsToDraw.get(randomIndex);
        return currentRandomItem.getEntry();
    }

    public boolean isAnswerCorrect(String answer) {
        boolean isCorrect = currentRandomItem.containsTranslation(answer);
        if (isCorrect) {
            itemsToDraw.remove(currentRandomItem);
        }
        return isCorrect;
    }

    public void fillItemsToDraw() {
        this.itemsToDraw = new ArrayList<>();
        this.itemsToDraw.addAll(items);
    }

    public boolean isAnyLeft() {
        return !itemsToDraw.isEmpty();
    }
}
