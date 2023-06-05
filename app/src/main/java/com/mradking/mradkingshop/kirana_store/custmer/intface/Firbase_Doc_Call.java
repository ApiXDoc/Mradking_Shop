package com.mradking.mradkingshop.kirana_store.custmer.intface;

import com.google.firebase.firestore.DocumentSnapshot;

public interface Firbase_Doc_Call {
    void onSuccess(DocumentSnapshot documentSnapshot);
    void onFailure(String error);

}
