package com.mradking.mradkingshop.kirana_store.custmer.Model;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mradking.mradkingshop.kirana_store.shopkeer.modal.AddedProductModel;

import java.util.List;

public class ListModal {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    public DocumentSnapshot lastVisible;
    private Boolean isFirstPageFirstLoad = true;
    public ListModal(){

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();


    }


    public void  get_list( String orintation_vertical,boolean orintation_end
                        ,Query query  ,List list, RecyclerView recyclerView,RecyclerView.Adapter adapter,Context context){

       LinearLayoutManager lm1= lenear_layout_set(orintation_end,orintation_vertical,context);



        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lm1);
        recyclerView.setAdapter(adapter);



        if (firebaseAuth.getCurrentUser() != null) {

            firebaseFirestore = FirebaseFirestore.getInstance();

            RecyclerView.Adapter finalAdapter1 = adapter;
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    Boolean reachedBottom = !recyclerView.canScrollVertically(1);

                    if (reachedBottom) {


                        String desc = lastVisible.getString("name");

                        loadMorePost(list, finalAdapter1,query);

                    }

                }
            });


            Query firstQuery =  query.limit(10);




            RecyclerView.Adapter finalAdapter = adapter;
            firstQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {


                    Log.d("error", String.valueOf(e));

                    if (isFirstPageFirstLoad) {

                        if (documentSnapshots.isEmpty()) {




                        } else {


                            lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);


                        }


                    }
                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {

                            CommonModal blogPost = doc.getDocument().toObject(CommonModal.class).withId(doc.getDocument().getId());
                            String itemId = doc.getDocument().getId();

                            blogPost.setItemId(itemId);


                            if (isFirstPageFirstLoad) {
                                list.add(0, blogPost);
                            } else {

                                list.add(0, blogPost);
                            }


                            finalAdapter.notifyDataSetChanged();

                        }
                    }

                    isFirstPageFirstLoad = false;

                }
            });

        } else {

            Toast.makeText(context, "no user", Toast.LENGTH_SHORT).show();
        }





    }

    private void loadMorePost(List list, RecyclerView.Adapter finalAdapter1, Query query) {



        Query nextQuery = query
                .startAfter(lastVisible).limit(10);




        nextQuery.addSnapshotListener( new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (!documentSnapshots.isEmpty()) {

                    lastVisible = documentSnapshots.getDocuments().get(documentSnapshots.size() - 1);
                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {

                            CommonModal govt_job = doc.getDocument().toObject(CommonModal.class);
                            String itemId = doc.getDocument().getId();

                            govt_job.setItemId(itemId);

                            list.add(govt_job);

                            finalAdapter1.notifyDataSetChanged();

                        }
                    }

                }

            }
        });


    }

    private LinearLayoutManager lenear_layout_set(boolean orintation_end, String orintation_vertical,Context context) {

        if(orintation_end=true && orintation_vertical.contentEquals("yes")){

            LinearLayoutManager lm1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            lm1.setReverseLayout(true);
            lm1.setStackFromEnd(true);
            return  lm1;
        }

        if(orintation_end=false && orintation_vertical.contentEquals("yes")){

            LinearLayoutManager lm1 = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            lm1.setReverseLayout(false);
            lm1.setStackFromEnd(false);
            return  lm1;
        }


        if(orintation_end=true && orintation_vertical.contentEquals("no")){

            LinearLayoutManager lm1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            lm1.setReverseLayout(true);
            lm1.setStackFromEnd(true);
            return  lm1;
        }

        if(orintation_end=false && orintation_vertical.contentEquals("no")){

            LinearLayoutManager lm1 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            lm1.setReverseLayout(false);
            lm1.setStackFromEnd(false);
            return  lm1;
        }

        return  null;
    }

}
