package services;

import model.Song;
import repositories.SongRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class SongService {

    SongRepository repository = new SongRepository();

    public Song addSong(Song song){
        return repository.saveSong(song);
    }

    public List<Song> getAllSongs(){
        return repository.getAllSongs();
    }

    public Song getSongById(String id) {
        Optional<Song> songResult = repository.getSongById(id);
        return songResult.orElseThrow(NoSuchElementException::new);
    }

}
