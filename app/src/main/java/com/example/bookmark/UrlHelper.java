package com.example.bookmark;

import android.widget.EditText;
import android.widget.RatingBar;

import com.example.bookmark.model.Url;

public class UrlHelper {
    private final EditText fieldUrl;
    private final EditText fieldObservacao;
    private final RatingBar fieldRanking;

    private Url url;

    public UrlHelper(UrlActivity activity) {
        fieldUrl = activity.findViewById(R.id.txtUrl);
        fieldObservacao = activity.findViewById(R.id.txtObs);
        fieldRanking = activity.findViewById(R.id.stars);
        url = new Url();
    }

    public Url getUrl() {
        url.setUrl(fieldUrl.getText().toString());
        url.setObservacao(fieldObservacao.getText().toString());
        url.setRanking(Double.valueOf(fieldRanking.getProgress()));
        return url;
    }

    public void setUrl(Url url) {
        fieldUrl.setText(url.getUrl());
        fieldObservacao.setText(url.getObservacao());
        fieldRanking.setProgress(url.getRanking().intValue());
        this.url = url;
    }
}
