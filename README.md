# TP - Maven

## 📘 "BookReviewManager" – Gestionnaire de critiques de livres

---

## 🧱 Cahier des charges fonctionnel

L'application devra permettre les fonctionnalités suivantes :

### 1. **Ajout de livres**

Chaque livre possède :

- un identifiant (UUID ou auto-incrément),
- un titre,
- un auteur,
- une année de publication,
- un genre littéraire (enum : SCIENCE_FICTION, POLICIER, FANTASY, CLASSIQUE, etc.).

### 2. **Ajout de critiques**

Chaque critique comporte :

- l’identifiant du livre concerné,
- un pseudo de lecteur,
- une note (entre 0 et 5),
- un commentaire textuel,
- une date.

### 3. **Fonctionnalités métier à implémenter avec Stream API**

- Afficher la **liste des livres triés par moyenne des notes**.
- Afficher les **critiques d’un auteur donné**, triées par date décroissante.
- Rechercher les **meilleurs livres par genre**, avec la meilleure moyenne.
- Filtrer les livres publiés **avant une certaine année** et afficher leurs critiques.
- Grouper les livres par **genre** et afficher, pour chaque groupe, le nombre de livres et la note moyenne globale.

---

## 🔧 Contraintes techniques

- Projet Java géré avec **Maven** (création de projet avec `archetype-quickstart`).
- Les données (livres et critiques) seront **injectées manuellement** dans un fichier `FakeDatabase.java` à base de `List<>`.

---

## Fichier `FakeDatabase.java`

```java
package data;

import model.Book;
import model.Genre;
import model.Review;

import java.time.LocalDate;
import java.util.List;

public class FakeDatabase {
    public static List<Book> getBooks() {
        return List.of(
            new Book(1, "Dune", "Frank Herbert", 1965, Genre.SCIENCE_FICTION),
            new Book(2, "1984", "George Orwell", 1949, Genre.SCIENCE_FICTION),
            new Book(3, "Le Seigneur des Anneaux", "J.R.R. Tolkien", 1954, Genre.FANTASY),
            new Book(4, "Le Meurtre de Roger Ackroyd", "Agatha Christie", 1926, Genre.POLICIER)
        );
    }

    public static List<Review> getReviews() {
        return List.of(
            new Review(1, "Alice", 4.5, "Très bon livre", LocalDate.of(2022, 5, 1)),
            new Review(1, "Bob", 5.0, "Chef-d’œuvre", LocalDate.of(2023, 3, 12)),
            new Review(2, "Charlie", 4.0, "Visionnaire", LocalDate.of(2021, 11, 20)),
            new Review(3, "Alice", 5.0, "Incroyable", LocalDate.of(2022, 1, 10)),
            new Review(4, "Bob", 3.5, "Intéressant", LocalDate.of(2020, 7, 4)),
            new Review(4, "Alice", 4.0, "Classique efficace", LocalDate.of(2022, 8, 15))
        );
    }
}
```

## 💻 Installation

1. Cloner ce dépôt :

```bash
git clone <url-du-depot>
cd BookReviewManager```

2. Compiler avec Maven
```bash
mvn clean compile```

3.Lancer l'application
``bash
`mvn exec:java -Dexec.mainClass="app.App"```
