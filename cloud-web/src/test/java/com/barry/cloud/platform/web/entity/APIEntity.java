package com.barry.cloud.platform.web.entity;

import java.util.List;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/9/3 10:19
 */
public class APIEntity {
    /**
     * code : 0
     * detail :
     * content : null
     * result : null
     * data : [{"publishDate":"2018-08-27T12:00:00","serverDate":"2018-08-27T16:03:26","location":{"admincode":330100,"adminname":"杭州市"},"currentWeather":{"time":"2018-08-27T15:10:31","weather":"1","temperature":"33.0","windDirection":"8","windPower":"2","humidity":"64.0"},"forecast":[{"time":"2018-08-27T11:00:01","dayWeather":"1","nightWeather":"1","dayTemperature":"33.0","nightTemperature":"26.0","dayWindDirection":"1","nightWindDirection":"2","dayWindPower":"1","nightWindPower":"1","sunRiseTime":"05:32","sunSetTime":"18:29","indices":null,"h24weather":[{"time":"10:00:00","weather":"1","temperature":"31.0","windDirection":"1","windPower":"3","humidity":"78.0"},{"time":"16:00:00","weather":"1","temperature":"32.0","windDirection":"1","windPower":"7","humidity":"78.0"},{"time":"22:00:00","weather":"1","temperature":"28.0","windDirection":"2","windPower":"7","humidity":"80.0"},{"time":"23:00:00","weather":"1","temperature":"28.0","windDirection":"2","windPower":"7","humidity":"81.0"}]},{"time":"2018-08-28T11:00:01","dayWeather":"1","nightWeather":"1","dayTemperature":"34.0","nightTemperature":"26.0","dayWindDirection":"2","nightWindDirection":"2","dayWindPower":"1","nightWindPower":"1","sunRiseTime":"05:33","sunSetTime":"18:28","indices":null,"h24weather":null},{"time":"2018-08-29T11:00:01","dayWeather":"1","nightWeather":"1","dayTemperature":"33.0","nightTemperature":"26.0","dayWindDirection":"2","nightWindDirection":"2","dayWindPower":"0","nightWindPower":"0","sunRiseTime":"05:33","sunSetTime":"18:27","indices":null,"h24weather":null},{"time":"2018-08-30T11:00:01","dayWeather":"3","nightWeather":"1","dayTemperature":"31.0","nightTemperature":"26.0","dayWindDirection":"2","nightWindDirection":"2","dayWindPower":"0","nightWindPower":"0","sunRiseTime":"05:34","sunSetTime":"18:26","indices":null,"h24weather":null},{"time":"2018-08-31T11:00:01","dayWeather":"3","nightWeather":"3","dayTemperature":"32.0","nightTemperature":"25.0","dayWindDirection":"4","nightWindDirection":"4","dayWindPower":"0","nightWindPower":"0","sunRiseTime":"05:34","sunSetTime":"18:25","indices":null,"h24weather":null}],"index":{"time":"2018-08-27T11:00:01","indices":[{"type":"22","level":"2","desc":"Hot","detail":""},{"type":"19","level":"1","desc":"BurningHot","detail":""},{"type":"3","level":"2","desc":"LessSuitable","detail":""},{"type":"9","level":"3","desc":"LessUnsuitable","detail":""}]},"weatherAir":null}]
     */

    private String code;
    private String detail;
    private Object content;
    private Object result;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * publishDate : 2018-08-27T12:00:00
         * serverDate : 2018-08-27T16:03:26
         * location : {"admincode":330100,"adminname":"杭州市"}
         * currentWeather : {"time":"2018-08-27T15:10:31","weather":"1","temperature":"33.0","windDirection":"8","windPower":"2","humidity":"64.0"}
         * forecast : [{"time":"2018-08-27T11:00:01","dayWeather":"1","nightWeather":"1","dayTemperature":"33.0","nightTemperature":"26.0","dayWindDirection":"1","nightWindDirection":"2","dayWindPower":"1","nightWindPower":"1","sunRiseTime":"05:32","sunSetTime":"18:29","indices":null,"h24weather":[{"time":"10:00:00","weather":"1","temperature":"31.0","windDirection":"1","windPower":"3","humidity":"78.0"},{"time":"16:00:00","weather":"1","temperature":"32.0","windDirection":"1","windPower":"7","humidity":"78.0"},{"time":"22:00:00","weather":"1","temperature":"28.0","windDirection":"2","windPower":"7","humidity":"80.0"},{"time":"23:00:00","weather":"1","temperature":"28.0","windDirection":"2","windPower":"7","humidity":"81.0"}]},{"time":"2018-08-28T11:00:01","dayWeather":"1","nightWeather":"1","dayTemperature":"34.0","nightTemperature":"26.0","dayWindDirection":"2","nightWindDirection":"2","dayWindPower":"1","nightWindPower":"1","sunRiseTime":"05:33","sunSetTime":"18:28","indices":null,"h24weather":null},{"time":"2018-08-29T11:00:01","dayWeather":"1","nightWeather":"1","dayTemperature":"33.0","nightTemperature":"26.0","dayWindDirection":"2","nightWindDirection":"2","dayWindPower":"0","nightWindPower":"0","sunRiseTime":"05:33","sunSetTime":"18:27","indices":null,"h24weather":null},{"time":"2018-08-30T11:00:01","dayWeather":"3","nightWeather":"1","dayTemperature":"31.0","nightTemperature":"26.0","dayWindDirection":"2","nightWindDirection":"2","dayWindPower":"0","nightWindPower":"0","sunRiseTime":"05:34","sunSetTime":"18:26","indices":null,"h24weather":null},{"time":"2018-08-31T11:00:01","dayWeather":"3","nightWeather":"3","dayTemperature":"32.0","nightTemperature":"25.0","dayWindDirection":"4","nightWindDirection":"4","dayWindPower":"0","nightWindPower":"0","sunRiseTime":"05:34","sunSetTime":"18:25","indices":null,"h24weather":null}]
         * index : {"time":"2018-08-27T11:00:01","indices":[{"type":"22","level":"2","desc":"Hot","detail":""},{"type":"19","level":"1","desc":"BurningHot","detail":""},{"type":"3","level":"2","desc":"LessSuitable","detail":""},{"type":"9","level":"3","desc":"LessUnsuitable","detail":""}]}
         * weatherAir : null
         */

        private String publishDate;
        private String serverDate;
        private LocationBean location;
        private CurrentWeatherBean currentWeather;
        private IndexBean index;
        private Object weatherAir;
        private List<ForecastBean> forecast;

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getServerDate() {
            return serverDate;
        }

        public void setServerDate(String serverDate) {
            this.serverDate = serverDate;
        }

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public CurrentWeatherBean getCurrentWeather() {
            return currentWeather;
        }

        public void setCurrentWeather(CurrentWeatherBean currentWeather) {
            this.currentWeather = currentWeather;
        }

        public IndexBean getIndex() {
            return index;
        }

        public void setIndex(IndexBean index) {
            this.index = index;
        }

        public Object getWeatherAir() {
            return weatherAir;
        }

        public void setWeatherAir(Object weatherAir) {
            this.weatherAir = weatherAir;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class LocationBean {
            /**
             * admincode : 330100
             * adminname : 杭州市
             */

            private int admincode;
            private String adminname;

            public int getAdmincode() {
                return admincode;
            }

            public void setAdmincode(int admincode) {
                this.admincode = admincode;
            }

            public String getAdminname() {
                return adminname;
            }

            public void setAdminname(String adminname) {
                this.adminname = adminname;
            }
        }

        public static class CurrentWeatherBean {
            /**
             * time : 2018-08-27T15:10:31
             * weather : 1
             * temperature : 33.0
             * windDirection : 8
             * windPower : 2
             * humidity : 64.0
             */

            private String time;
            private String weather;
            private String temperature;
            private String windDirection;
            private String windPower;
            private String humidity;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWindDirection() {
                return windDirection;
            }

            public void setWindDirection(String windDirection) {
                this.windDirection = windDirection;
            }

            public String getWindPower() {
                return windPower;
            }

            public void setWindPower(String windPower) {
                this.windPower = windPower;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }
        }

        public static class IndexBean {
            /**
             * time : 2018-08-27T11:00:01
             * indices : [{"type":"22","level":"2","desc":"Hot","detail":""},{"type":"19","level":"1","desc":"BurningHot","detail":""},{"type":"3","level":"2","desc":"LessSuitable","detail":""},{"type":"9","level":"3","desc":"LessUnsuitable","detail":""}]
             */

            private String time;
            private List<IndicesBean> indices;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public List<IndicesBean> getIndices() {
                return indices;
            }

            public void setIndices(List<IndicesBean> indices) {
                this.indices = indices;
            }

            public static class IndicesBean {
                /**
                 * type : 22
                 * level : 2
                 * desc : Hot
                 * detail :
                 */

                private String type;
                private String level;
                private String desc;
                private String detail;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getLevel() {
                    return level;
                }

                public void setLevel(String level) {
                    this.level = level;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }
            }
        }

        public static class ForecastBean {
            /**
             * time : 2018-08-27T11:00:01
             * dayWeather : 1
             * nightWeather : 1
             * dayTemperature : 33.0
             * nightTemperature : 26.0
             * dayWindDirection : 1
             * nightWindDirection : 2
             * dayWindPower : 1
             * nightWindPower : 1
             * sunRiseTime : 05:32
             * sunSetTime : 18:29
             * indices : null
             * h24weather : [{"time":"10:00:00","weather":"1","temperature":"31.0","windDirection":"1","windPower":"3","humidity":"78.0"},{"time":"16:00:00","weather":"1","temperature":"32.0","windDirection":"1","windPower":"7","humidity":"78.0"},{"time":"22:00:00","weather":"1","temperature":"28.0","windDirection":"2","windPower":"7","humidity":"80.0"},{"time":"23:00:00","weather":"1","temperature":"28.0","windDirection":"2","windPower":"7","humidity":"81.0"}]
             */

            private String time;
            private String dayWeather;
            private String nightWeather;
            private String dayTemperature;
            private String nightTemperature;
            private String dayWindDirection;
            private String nightWindDirection;
            private String dayWindPower;
            private String nightWindPower;
            private String sunRiseTime;
            private String sunSetTime;
            private Object indices;
            private List<H24weatherBean> h24weather;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getDayWeather() {
                return dayWeather;
            }

            public void setDayWeather(String dayWeather) {
                this.dayWeather = dayWeather;
            }

            public String getNightWeather() {
                return nightWeather;
            }

            public void setNightWeather(String nightWeather) {
                this.nightWeather = nightWeather;
            }

            public String getDayTemperature() {
                return dayTemperature;
            }

            public void setDayTemperature(String dayTemperature) {
                this.dayTemperature = dayTemperature;
            }

            public String getNightTemperature() {
                return nightTemperature;
            }

            public void setNightTemperature(String nightTemperature) {
                this.nightTemperature = nightTemperature;
            }

            public String getDayWindDirection() {
                return dayWindDirection;
            }

            public void setDayWindDirection(String dayWindDirection) {
                this.dayWindDirection = dayWindDirection;
            }

            public String getNightWindDirection() {
                return nightWindDirection;
            }

            public void setNightWindDirection(String nightWindDirection) {
                this.nightWindDirection = nightWindDirection;
            }

            public String getDayWindPower() {
                return dayWindPower;
            }

            public void setDayWindPower(String dayWindPower) {
                this.dayWindPower = dayWindPower;
            }

            public String getNightWindPower() {
                return nightWindPower;
            }

            public void setNightWindPower(String nightWindPower) {
                this.nightWindPower = nightWindPower;
            }

            public String getSunRiseTime() {
                return sunRiseTime;
            }

            public void setSunRiseTime(String sunRiseTime) {
                this.sunRiseTime = sunRiseTime;
            }

            public String getSunSetTime() {
                return sunSetTime;
            }

            public void setSunSetTime(String sunSetTime) {
                this.sunSetTime = sunSetTime;
            }

            public Object getIndices() {
                return indices;
            }

            public void setIndices(Object indices) {
                this.indices = indices;
            }

            public List<H24weatherBean> getH24weather() {
                return h24weather;
            }

            public void setH24weather(List<H24weatherBean> h24weather) {
                this.h24weather = h24weather;
            }

            public static class H24weatherBean {
                /**
                 * time : 10:00:00
                 * weather : 1
                 * temperature : 31.0
                 * windDirection : 1
                 * windPower : 3
                 * humidity : 78.0
                 */

                private String time;
                private String weather;
                private String temperature;
                private String windDirection;
                private String windPower;
                private String humidity;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getWeather() {
                    return weather;
                }

                public void setWeather(String weather) {
                    this.weather = weather;
                }

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getWindDirection() {
                    return windDirection;
                }

                public void setWindDirection(String windDirection) {
                    this.windDirection = windDirection;
                }

                public String getWindPower() {
                    return windPower;
                }

                public void setWindPower(String windPower) {
                    this.windPower = windPower;
                }

                public String getHumidity() {
                    return humidity;
                }

                public void setHumidity(String humidity) {
                    this.humidity = humidity;
                }
            }
        }
    }
}
