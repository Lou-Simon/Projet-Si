export const API_ENDPOINTS = {
    artists:      'http://localhost:12061/api/artists',   
    artistCreate: 'http://localhost:12061/api/artists',
    artistsBatch: 'http://localhost:12061/api/artists/batch',

    moviesBatch:  'http://localhost:12061/api/movies/batch',
    movies:       'http://localhost:11041/api/movies',

    reviews:      'http://localhost:12061/api/reviews',

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
    create:   '',           
    getByPseudo: '/:pseudo', 
    update:   '/:pseudo',   
    delete:   '/:pseudo',   
}

export const REVIEW_API = {
    baseUrl:  'http://localhost:12061/api/reviews',
    getAll:   '',         
    getOne:   '/:id',       
    create:   '',           
    update:   '/:id',       
    delete:   '/:id',      
}

export const SCRAPING_API = {
    baseUrl:      'http://localhost:12061/api',
    movies:       '/movies',       
    movieById:    '/movies/:id',
    artists:      '/artists',     
    artistById:   '/artists/:id',
}