# Projet SI — Plateforme VOD Microservices

Application de vidéo à la demande construite sur une architecture microservices, permettant de gérer films, artistes, utilisateurs, réservations, paiements et avis.

---

## Architecture

```
Client (12060)
    └── API Gateway (12061)           ← point d'entrée unique, auth JWT
            ├── api-scrapping (12062) ← import films & artistes depuis sources externes
            ├── api-user (12063)      ← gestion des comptes utilisateurs + authentification
            ├── api-artist (12064)    ← base de données des artistes
            ├── api-review (12065)    ← avis et notes sur les films
            ├── api-movie             ← catalogue de films
            ├── api-payment           ← gestion des paiements
            ├── api-reservation       ← réservations des films
            └── api-poster            ← posters associés aux films
```

Chaque service est indépendant, déployable séparément, et communique via la gateway.

Ici, ne sont spécifiés que les ports qui nous sont connus, aucune information concernant les autres ports ne nous a été transmise pour le moment.

---

## Lancer le projet

```bash
# Lancer tous les services via Docker
docker-compose up --build
```

---

## Convention de nommage des commits

| Préfixe | Usage | Exemple |
|---------|-------|---------|
| `feat` | Nouvelle fonctionnalité | `feat: ajout de la page de recherche` |
| `fix` | Correction de bug | `fix: correction du filtre JWT` |
| `docs` | Documentation | `docs: mise à jour du README` |

---

## Stack technique

- **Java / Spring Boot** — microservices & gateway (Spring Cloud Gateway)
- **JWT** — authentification centralisée dans la gateway
- **Docker / Docker Compose** — orchestration des services

Dédicace au bios, ti é le sang
