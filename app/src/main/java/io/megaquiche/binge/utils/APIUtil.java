package io.megaquiche.binge.utils;

import io.megaquiche.binge.pojo.Series;

/**
 * Created by Timo Maemecke (@timomeh) on 02/06/16.
 * <p/>
 * TODO: Add a class header comment
 */
public class APIUtil {
    public static class Lang {
        public interface Check<T> {
            void onFinished(T result);
        }

        public static void checkSeries(final Series series, final Check<Series> check) {
            if (series.getDescription().isEmpty()) {
                API.Req.getSeries(series.getId(), "en", new API.Res<Series>() {
                    @Override
                    public void onSuccess(Series result) {
                        series.setDescription(result.getDescription());
                        check.onFinished(series);
                    }

                    @Override
                    public void onError() {
                        check.onFinished(series);
                    }
                });
            }
        }
    }
}
