package com.joannas.swing.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class Item {
    private String entry;
    private Collection<String> translations;

    public String getEntry() {
        return entry;
    }

    public Item(String entryWithTranslations) {
        String[] splitted = entryWithTranslations.split(",");
        this.entry = splitted[0];
        this.translations = new HashSet<>();
        for (int i = 1; i < splitted.length; i++) {
            this.translations.add(splitted[i]);
        }

        //translations.addAll(Arrays.asList(splitted).subList(1, splitted.length));
    }

    @Override
    public String toString() {
        return "\n" + this.entry + "->" + this.translations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return this.entry.equalsIgnoreCase(item.entry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.entry.toLowerCase());
    }

    public boolean containsTranslation(String answer) {
        return this.translations.contains(answer);
    }
}
