package com.houlin.capstone_project.data.bean;

import java.util.List;

/**
 * @author houlin
 */

public class Detail {
    /**
     * rating : {"max":10,"average":6.1,"stars":"30","min":0}
     * reviews_count : 1229
     * wish_count : 12433
     * douban_site :
     * year : 2017
     * images : {"small":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2508926717.jpg","large":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2508926717.jpg","medium":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2508926717.jpg"}
     * alt : https://movie.douban.com/subject/26662193/
     * id : 26662193
     * mobile_url : https://movie.douban.com/subject/26662193/mobile
     * title : 前任3：再见前任
     * do_count : null
     * share_url : http://m.douban.com/movie/subject/26662193
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/26662193/cinema/
     * episodes_count : null
     * countries : ["中国大陆"]
     * genres : ["喜剧","爱情"]
     * collect_count : 34799
     * casts : [{"alt":"https://movie.douban.com/celebrity/1275667/","avatars":{"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14025.jpg","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14025.jpg","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14025.jpg"},"name":"韩庚","id":"1275667"},{"alt":"https://movie.douban.com/celebrity/1275564/","avatars":{"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1366015827.84.jpg","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1366015827.84.jpg","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1366015827.84.jpg"},"name":"郑恺","id":"1275564"},{"alt":"https://movie.douban.com/celebrity/1342252/","avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1408089064.98.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1408089064.98.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1408089064.98.jpg"},"name":"于文文","id":"1342252"},{"alt":"https://movie.douban.com/celebrity/1376370/","avatars":{"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1501850512.53.jpg","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1501850512.53.jpg","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1501850512.53.jpg"},"name":"曾梦雪","id":"1376370"}]
     * current_season : null
     * original_title : 前任3：再见前任
     * summary : 一对好基友孟云（韩庚 饰）和余飞（郑恺 饰）跟女友都因为一点小事宣告分手，并且“拒绝挽回，死不认错”。两人在夜店、派对与交友软件上放飞人生第二春，大肆庆祝“黄金单身期”，从而引发了一系列好笑的故事。孟云与女友同甘共苦却难逃“五年之痒”，余飞与女友则棋逢敌手相爱相杀无绝期。然而现实的“打脸”却来得猝不及防：一对推拉纠结零往来，一对纠缠互怼全交代。两对恋人都将面对最终的选择：是再次相见？还是再也不见？
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1332171/","avatars":{"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1391439365.41.jpg","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1391439365.41.jpg","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1391439365.41.jpg"},"name":"田羽生","id":"1332171"}]
     * comments_count : 17854
     * ratings_count : 33407
     * aka : ["前任3","前任攻略3","前任3：颜值大作战","The Ex-File: The Return of the Exes"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private Object do_count;
    private String share_url;
    private Object seasons_count;
    private String schedule_url;
    private Object episodes_count;
    private int collect_count;
    private Object current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<DirectorsBean> directors;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getDo_count() {
        return do_count;
    }

    public void setDo_count(Object do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public Object getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Object seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Object episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(Object current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<CastsBean> getCasts() {
        return casts;
    }

    public void setCasts(List<CastsBean> casts) {
        this.casts = casts;
    }

    public List<DirectorsBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<DirectorsBean> directors) {
        this.directors = directors;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }

    public static class RatingBean {
        /**
         * max : 10
         * average : 6.1
         * stars : 30
         * min : 0
         */

        private int max;
        private double average;
        private String stars;
        private int min;

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getAverage() {
            return average;
        }

        public void setAverage(double average) {
            this.average = average;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public static class ImagesBean {
        /**
         * small : http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2508926717.jpg
         * large : http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2508926717.jpg
         * medium : http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2508926717.jpg
         */

        private String small;
        private String large;
        private String medium;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }
    }

    public static class CastsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1275667/
         * avatars : {"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14025.jpg","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14025.jpg","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14025.jpg"}
         * name : 韩庚
         * id : 1275667
         */

        private String alt;
        private AvatarsBean avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBean getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBean avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBean {
            /**
             * small : http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14025.jpg
             * large : http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14025.jpg
             * medium : http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14025.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }

    public static class DirectorsBean {
        /**
         * alt : https://movie.douban.com/celebrity/1332171/
         * avatars : {"small":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1391439365.41.jpg","large":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1391439365.41.jpg","medium":"http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1391439365.41.jpg"}
         * name : 田羽生
         * id : 1332171
         */

        private String alt;
        private AvatarsBeanX avatars;
        private String name;
        private String id;

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public AvatarsBeanX getAvatars() {
            return avatars;
        }

        public void setAvatars(AvatarsBeanX avatars) {
            this.avatars = avatars;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public static class AvatarsBeanX {
            /**
             * small : http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1391439365.41.jpg
             * large : http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1391439365.41.jpg
             * medium : http://img7.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1391439365.41.jpg
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }
    }
}
