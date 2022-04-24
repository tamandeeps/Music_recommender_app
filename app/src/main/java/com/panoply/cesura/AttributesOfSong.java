package com.panoply.cesura;

/**
 * Created by eeshwarg on 03-04-2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
//import com.echonest.api.v4.Song;
import com.echonest.api.v4.Song;
import com.echonest.api.v4.SongParams;
import java.util.List;


public class AttributesOfSong{

    private EchoNestAPI en;
    private static final String TAG = "AttributesOfSong";

    public AttributesOfSong(Context context) throws EchoNestException {
        en = new EchoNestAPI(context.getString(R.string.EchoNest_API_Key));
        en.setTraceSends(true);
        en.setTraceRecvs(false);
    }

    public TrackScore getAttributes(Pair<String,String> pair)
            throws EchoNestException {
        Log.d(TAG, "Getting attributes of Song " + pair.getLeft());
        SongParams p = new SongParams();
        p.setArtist(pair.getRight());
        p.setTitle(pair.getLeft());
        p.setResults(1);
        p.includeAudioSummary();

        TrackScore track = new TrackScore();
        List<Song> songs = en.searchSongs(p);
        if (songs.size() > 0) {
            int key = songs.get(0).getKey();
            track.setKey(key);

            float tempo = (float)songs.get(0).getTempo();
            track.setTempo(tempo);

            int timeSignature = songs.get(0).getTimeSignature();
            track.setTimeSignature(timeSignature);

            float loudness = (float)songs.get(0).getLoudness();
            track.setLoudness(loudness);

            float energy = (float)songs.get(0).getEnergy();
            track.setEnergy(energy);

            float danceability = (float)songs.get(0).getDanceability();
            track.setDanceability(danceability);
        }

        return track;
    }

}
