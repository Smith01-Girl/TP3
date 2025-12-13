# TP3
MicroServices

## Équipe
- **[Grace Ancia Kodjogbe-Mbemba]** - Module Utilisateurs
- **[Jeremy Tardif-Blanchet]** - Module Nouvelles
- **[Crepin La Paix Hemle Djob Sotong]** - Module Critères


## Architecture
```
Gateway (8080) → service-utilisateurs (8081)
                → service-nouvelles (8082)
                → service-criteres (8083)
                -> service-statistiques (8084)
                     ↓
                  MySQL (3306)
```


### Développement
```bash
docker-compose -f docker-compose.dev.yml up --build
```

**Accès :**
- Gateway: http://localhost:8080
- Eureka: http://localhost:8761
- service-utilisateurs: http://localhost:8081
- MySQL: localhost:3306 (user: tp3, password: 1234)


### Production
```bash
docker-compose -f docker-compose.prod.yml up --build
```

**Accès :** Gateway uniquement sur http://localhost:8080

## Module Utilisateurs

### Endpoints
- `GET /api/users` - Liste
- `GET /api/users/{id}` - Détails
- `POST /api/users` - Créer
- `PUT /api/users/{id}` - Modifier
- `DELETE /api/users/{id}` - Supprimer

### Permissions (pour autres services)
- `GET /api/users/{userId}/permissions/can-create-news`
- `GET /api/users/{userId}/permissions/can-modify-news`
- `GET /api/users/{userId}/permissions/can-delete-news`
- `GET /api/users/{userId}/permissions/can-create-criteria`
- `POST /api/users/{userId}/increment-articles`
- `GET /api/users/{userId}/journalist-level`

