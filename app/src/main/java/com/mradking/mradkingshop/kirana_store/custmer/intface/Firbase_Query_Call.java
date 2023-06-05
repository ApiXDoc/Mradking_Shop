package com.mradking.mradkingshop.kirana_store.custmer.intface;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public interface Firbase_Query_Call {

    void onSuccess(QuerySnapshot documentSnapshot);
    void onFailure(String error);


}
