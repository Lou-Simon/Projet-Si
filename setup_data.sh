#!/bin/bash

# Configuration des ports
GATEWAY_URL="http://localhost:12061"

echo "Initialisation du jeu de donnees VOD..."

# 1. UTILISATEURS (api-user : 12063)
echo "Creation des comptes utilisateurs..."

# Tableau des utilisateurs [pseudo, nom, prenom, age, adresse]
users=(
  "g_alizan;Alizan;Gaspard;22;14 rue du Debardeur, Le Havre"
  "a_air;Air;Axel;30;8 impasse du Vent, Lille"
  "p_hichet;Hichet;Pat;45;2 avenue du Rail, Lyon"
  "s_mosh;Mosh;Sam;19;66 route du Rock, Clisson"
  "j_tame;Tame;Justin;28;12 rue de la Justice, Rennes"
  "e_talle;Talle;Eve;35;40 rue du Sommeil, Paris"
  "a_miral;Miral;Anne;52;1 quai de la Marine, Brest"
)

for u in "${users[@]}"; do
  IFS=";" read -r pseudo nom prenom age adresse <<< "$u"
  curl -s -X POST "$GATEWAY_URL/user" \
  -H "Content-Type: application/json" \
  -d "{
    \"pseudo\": \"$pseudo\",
    \"nom\": \"$nom\",
    \"prenom\": \"$prenom\",
    \"age\": $age,
    \"adresse\": \"$adresse\",
    \"motDePasse\": \"password123\"
  }"
done 

# 2. SCRAPPING (api-scrapping : 12062)
echo "Importation via scrapping..."
# Import de films specifiques [cite: 43]
curl -s -X GET "$GATEWAY_URL/movies/Inception" [cite: 44]
curl -s -X GET "$GATEWAY_URL/movies/Interstellar"
curl -s -X GET "$GATEWAY_URL/movies/Pulp%20Fiction"
curl -s -X GET "$GATEWAY_URL/movies/The%20Dark%20Knight"

# Import d'artistes specifiques [cite: 46]
curl -s -X GET "$GATEWAY_URL/artists/Christopher%20Nolan"
curl -s -X GET "$GATEWAY_URL/artists/Quentin%20Tarantino"

# Import par lots (Batch) [cite: 49, 51]
curl -s -X GET "$GATEWAY_URL/movies/batch/10"
curl -s -X GET "$GATEWAY_URL/artists/batch/5"

# 3. ARTISTES (api-artist : 12064)
echo "Ajout manuel d'artistes..."
# Schema Artist : id, nom, genre, pays, dateNaissance, biographie [cite: 36, 41]
curl -s -X POST "$GATEWAY_URL/artist" -H "Content-Type: application/json" \
-d '{"id":"art-101","nom":"Gilles Gilet","genre":"Action","pays":"France","dateNaissance":"1980-01-01","biographie":"Specialiste des cascades en gilet pare-balles."}'

curl -s -X POST "$GATEWAY_URL/artist" -H "Content-Type: application/json" \
-d '{"id":"art-102","nom":"Sarah Vigote","genre":"Comedie","pays":"Belgique","dateNaissance":"1992-05-12","biographie":"Connue pour son humour tranchant."}'

# 4. REVIEWS (api-review : 12065)
echo "Ajout des reviews utilisateurs..."
# Schema Review : id, filmId, utilisateurId, note, titre, commentaire, datePublication [cite: 14, 21]

# Review de Gaspard Alizan
curl -s -X POST "$GATEWAY_URL/reviews" -H "Content-Type: application/json" \
-d '{"id":"rev-1","filmId":"Inception","utilisateurId":"g_alizan","note":8.5,"titre":"Un peu de gaz","commentaire":"Le film est fluide, on ne voit pas le temps passer.","datePublication":"2026-03-13T10:00:00Z"}' [cite: 15, 20]

# Review d'Axel Air
curl -s -X POST "$GATEWAY_URL/reviews" -H "Content-Type: application/json" \
-d '{"id":"rev-2","filmId":"Inception","utilisateurId":"a_air","note":10.0,"titre":"Rapide et efficace","commentaire":"Une vitesse d execution rare pour ce genre de scenario.","datePublication":"2026-03-13T11:00:00Z"}' [cite: 18, 19]

# Review de Pat Hichet
curl -s -X POST "$GATEWAY_URL/reviews" -H "Content-Type: application/json" \
-d '{"id":"rev-3","filmId":"Interstellar","utilisateurId":"p_hichet","note":2.0,"titre":"Je reste sur le quai","commentaire":"Trop de science, pas assez de fiction pour mon vieux coeur.","datePublication":"2026-03-13T12:00:00Z"}'

# Review de Sam Mosh
curl -s -X POST "$GATEWAY_URL/reviews" -H "Content-Type: application/json" \
-d '{"id":"rev-4","filmId":"Pulp Fiction","utilisateurId":"s_mosh","note":9.5,"titre":"Ca pulse","commentaire":"La BO est incroyable, ca donne envie de bouger.","datePublication":"2026-03-13T13:00:00Z"}'

echo "Operation terminee."
