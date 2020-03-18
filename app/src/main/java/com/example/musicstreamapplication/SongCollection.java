package com.example.musicstreamapplication;

public class SongCollection {

    private Song[] songs = new Song[3];

    public SongCollection(){
        prepareSong();
    }

    public Song getPrevSong(String currentSongId){
        Song song = null;
        for(int index = 0; index < songs.length; index++){
            String tempSongId = songs[index].getId();
            if(tempSongId.equals(currentSongId) && (index > 0)){
                song = songs[index - 1];
                break;
            }
        }
        return song;
    }

    public Song getNextSong(String currentSongId){
        Song song = null;
        for(int index = 0; index < songs.length; index++){
            String tempSongId = songs[index].getId();
            if(tempSongId.equals(currentSongId) && (index < songs.length - 1)){
                song = songs[index + 1];
                break;
            }
        }
        return song;
    }

    public Song searchById(String id){
        Song song = null;
        for(int index = 0; index < songs.length; index++) {
            song = songs[index];
            if(song.getId().equals(id)){
                return song;
            }
        }
        return song;
    }

    public Song searchByCoverArt(String coverArt)
    {
        Song song = null;
        for(int index = 0; index < songs.length; index++) {
            song = songs[index];
            if(song.getCoverArt().equals(coverArt)){
                return song;
            }
        }
        return song;
    }

    public Song searchByTitle(String title){
        Song song = null;
        for(int index = 0; index < songs.length; index++) {
            song = songs[index];
            if(song.getTitle().equals(title)){
                return song;
            }
        }
        return song;
    }

    private void prepareSong(){
        Song theWayYouLookTonight = new Song("s1001", "The Way You Look Tonight", "Michael Buble",
                "a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",
                4.66, "michael_buble_collection");

        Song billieJean = new Song("s1002", "Billie Jean", "Michael Jackson",
                "f504e6b8e037771318656394f532dede4f9bcaea?cid=2afe87a64b0042dabf51f37318616965",
                4.9, "billie_jean");
        Song smotri = new Song("s1003", "Смотри", "Полина Гагарина",
                "11bee4c2fdaeb87acf561a5d504a6cd1fdee45b8?cid=2afe87a64b0042dabf51f37318616965",
                3.51, "smotri_gagarina");
        songs[0] = theWayYouLookTonight;
        songs[1] = billieJean;
        songs[2] = smotri;
    }
}
