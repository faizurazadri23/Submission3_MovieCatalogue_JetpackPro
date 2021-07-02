package com.faizurazadri.moviecatalogue.utils;

import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.faizurazadri.moviecatalogue.data.source.remote.response.MovieResponse;
import com.faizurazadri.moviecatalogue.data.source.remote.response.TvShowResponse;

import java.util.ArrayList;

public class DataDummy {

    public static ArrayList<MoviesEntity> generateDummyMovies() {
        ArrayList<MoviesEntity> moviesEntities = new ArrayList<>();

        moviesEntities.add(new MoviesEntity("/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                "337404",
                "en",
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "5055.01",
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                "2021-05-26",
                "Cruella",
                "8.7",
                "2435"
        ));

        moviesEntities.add(new MoviesEntity("/qi6Edc1OPcyENecGtz8TF0DUr9e.jpg",
                "423108",
                "en",
                "The Conjuring: The Devil Made Me Do It",
                "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense..",
                "4432.301",
                "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
                "2021-05-25",
                "The Conjuring: The Devil Made Me Do It",
                "8.3",
                "1735"
        ));

        moviesEntities.add(new MoviesEntity("/7JENyUT8ABxcvrcijDBVpdjgCY9.jpg",
                "602734",
                "en",
                "Spiral: From the Book of Saw",
                "Working in the shadow of an esteemed police veteran, brash Detective Ezekiel “Zeke” Banks and his rookie partner take charge of a grisly investigation into murders that are eerily reminiscent of the city’s gruesome past.  Unwittingly entrapped in a deepening mystery, Zeke finds himself at the center of the killer’s morbid game.",
                "2178.434",
                "/lcyKve7nXRFgRyms9M1bndNkKOx.jpg",
                "2021-05-12",
                "Spiral: From the Book of Saw",
                "6.5",
                "295"
        ));

        moviesEntities.add(new MoviesEntity("/z2UtGA1WggESspi6KOXeo66lvLx.jpg",
                "520763",
                "en",
                "A Quiet Place Part II",
                "Following the events at home, the Abbott family now face the terrors of the outside world. Forced to venture into the unknown, they realize that the creatures that hunt by sound are not the only threats that lurk beyond the sand path.",
                "1852.826",
                "/4q2hz2m8hubgvijz8Ez0T2Os2Yv.jpg",
                "2021-05-21",
                "A Quiet Place Part II",
                "7.4",
                "180"
        ));


        moviesEntities.add(new MoviesEntity("/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "460465",
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "1648.249",
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                "7.5",
                "2952"
        ));


        return moviesEntities;
    }

    public static ArrayList<TvShowEntity> generateDummyTvShow() {
        ArrayList<TvShowEntity> tvShowEntities = new ArrayList<>();


        tvShowEntities.add(new TvShowEntity("/Afp8OhiO0Ajb3NPoCBvfu2pqaeO.jpg",
                "84958",
                "en",
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "1926.329",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                "2021-06-09",
                "Loki",
                "8.2",
                "1564"

        ));

        tvShowEntities.add(new TvShowEntity("/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg",
                "63174",
                "en",
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "1219.784",
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "2016-01-25",
                "Lucifer",
                "8.5",
                "9199"

        ));

        tvShowEntities.add(new TvShowEntity("/9Jmd1OumCjaXDkpllbSGi2EpJvl.jpg",
                "60735",
                "en",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "965.753",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "2014-10-07",
                "The Flash",
                "7.7",
                "7797"

        ));

        tvShowEntities.add(new TvShowEntity("/wu444tM9YBllq9UcBv5TeidO3j3.jpg",
                "91557",
                "no",
                "Ragnarok",
                "A small Norwegian town experiencing warm winters and violent downpours seems to be headed for another Ragnarök -- unless someone intervenes in time.",
                "999.107",
                "/xUtOM1QO4r8w8yeE00QvBdq58N5.jpg",
                "2020-01-31",
                "Ragnarok",
                "8",
                "464"

        ));


        return tvShowEntities;
    }

    public static ArrayList<MovieResponse> generateRemoteDummyMovies() {
        ArrayList<MovieResponse> moviesEntities = new ArrayList<>();

        moviesEntities.add(new MovieResponse("/6MKr3KgOLmzOP6MSuZERO41Lpkt.jpg",
                "337404",
                "en",
                "Cruella",
                "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
                "5055.01",
                "/rTh4K5uw9HypmpGslcKd4QfHl93.jpg",
                "2021-05-26",
                "Cruella",
                "8.7",
                "2435"
        ));

        moviesEntities.add(new MovieResponse("/qi6Edc1OPcyENecGtz8TF0DUr9e.jpg",
                "423108",
                "en",
                "The Conjuring: The Devil Made Me Do It",
                "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense..",
                "4432.301",
                "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
                "2021-05-25",
                "The Conjuring: The Devil Made Me Do It",
                "8.3",
                "1735"
        ));

        moviesEntities.add(new MovieResponse("/7JENyUT8ABxcvrcijDBVpdjgCY9.jpg",
                "602734",
                "en",
                "Spiral: From the Book of Saw",
                "Working in the shadow of an esteemed police veteran, brash Detective Ezekiel “Zeke” Banks and his rookie partner take charge of a grisly investigation into murders that are eerily reminiscent of the city’s gruesome past.  Unwittingly entrapped in a deepening mystery, Zeke finds himself at the center of the killer’s morbid game.",
                "2178.434",
                "/lcyKve7nXRFgRyms9M1bndNkKOx.jpg",
                "2021-05-12",
                "Spiral: From the Book of Saw",
                "6.5",
                "295"
        ));

        moviesEntities.add(new MovieResponse("/z2UtGA1WggESspi6KOXeo66lvLx.jpg",
                "520763",
                "en",
                "A Quiet Place Part II",
                "Following the events at home, the Abbott family now face the terrors of the outside world. Forced to venture into the unknown, they realize that the creatures that hunt by sound are not the only threats that lurk beyond the sand path.",
                "1852.826",
                "/4q2hz2m8hubgvijz8Ez0T2Os2Yv.jpg",
                "2021-05-21",
                "A Quiet Place Part II",
                "7.4",
                "180"
        ));


        moviesEntities.add(new MovieResponse("/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "460465",
                "en",
                "Mortal Kombat",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "1648.249",
                "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg",
                "2021-04-07",
                "Mortal Kombat",
                "7.5",
                "2952"
        ));

        return moviesEntities;
    }


    public static ArrayList<TvShowResponse> generateRemoteDummyTvShow() {
        ArrayList<TvShowResponse> tvShowEntities = new ArrayList<>();

        tvShowEntities.add(new TvShowResponse("/Afp8OhiO0Ajb3NPoCBvfu2pqaeO.jpg",
                "84958",
                "en",
                "Loki",
                "After stealing the Tesseract during the events of “Avengers: Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice: face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
                "1926.329",
                "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
                "2021-06-09",
                "Loki",
                "8.2",
                "1564"

        ));

        tvShowEntities.add(new TvShowResponse("/h48Dpb7ljv8WQvVdyFWVLz64h4G.jpg",
                "63174",
                "en",
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "1219.784",
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "2016-01-25",
                "Lucifer",
                "8.5",
                "9199"

        ));

        tvShowEntities.add(new TvShowResponse("/9Jmd1OumCjaXDkpllbSGi2EpJvl.jpg",
                "60735",
                "en",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "965.753",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "2014-10-07",
                "The Flash",
                "7.7",
                "7797"

        ));

        tvShowEntities.add(new TvShowResponse("/wu444tM9YBllq9UcBv5TeidO3j3.jpg",
                "91557",
                "no",
                "Ragnarok",
                "A small Norwegian town experiencing warm winters and violent downpours seems to be headed for another Ragnarök -- unless someone intervenes in time.",
                "999.107",
                "/xUtOM1QO4r8w8yeE00QvBdq58N5.jpg",
                "2020-01-31",
                "Ragnarok",
                "8",
                "464"

        ));

        return tvShowEntities;
    }

    public static MoviesEntity generateDummyFavoriteMovies(MoviesEntity moviesEntity, boolean status) {
        moviesEntity.setFavorite(status);
        return moviesEntity;
    }
}
