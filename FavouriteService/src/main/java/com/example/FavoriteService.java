package com.example;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;

    public FavoriteService(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    public Favorite addFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    public List<Favorite> getFavoritesByUsername(String username) {
        return favoriteRepository.findByUsername(username);
    }

    public void deleteFavorite(Long id) {
        favoriteRepository.deleteById(id);
    }
}
