export const API_ENDPOINTS = {
    // Artists
    artists:      'http://localhost:12061/api/artists',   
    artistCreate: 'http://localhost:12061/api/artists',
    artistsBatch: 'http://localhost:12061/api/artists/batch',

    // Movies (scraping)
    moviesBatch:  'http://localhost:12061/api/movies/batch',
    movies:       'http://localhost:12061/api/movies',

    // Reviews
    reviews:      'http://localhost:12061/api/reviews',

    // Users
    users:        'http://localhost:12061/api/user',
}

export const AUTH_API = {
    baseUrl:  'http://localhost:12061/api/auth',
    register: '/register',
    login:    '/login',
    logout:   '/logout',
    me:       '/me',
}

export const USER_API = {
    baseUrl:  'http://localhost:12061/api/user',
    create:   '',           // POST /api/user (public)
    getByPseudo: '/:pseudo', // GET /api/user/:pseudo (public)
    update:   '/:pseudo',   // PUT /api/user/:pseudo (protégé)
    delete:   '/:pseudo',   // DELETE /api/user/:pseudo (protégé)
}

export const REVIEW_API = {
    baseUrl:  'http://localhost:12061/api/reviews',
    getAll:   '',           // GET (public)
    getOne:   '/:id',       // GET (public)
    create:   '',           // POST (protégé, JWT requis)
    update:   '/:id',       // PUT (protégé, JWT requis)
    delete:   '/:id',       // DELETE (protégé, JWT requis)
}

export const SCRAPING_API = {
    baseUrl:      'http://localhost:12061/api',
    movies:       '/movies',        // GET /api/movies/**
    movieById:    '/movies/:id',
    artists:      '/artists',       // GET /api/artists/**
    artistById:   '/artists/:id',
}