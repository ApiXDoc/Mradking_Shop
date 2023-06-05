package com.mradking.mradkingshop.kirana_store.custmer.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mradking.mradkingshop.R;
import com.mradking.mradkingshop.kirana_store.custmer.Model.CommonModal;
import com.mradking.mradkingshop.kirana_store.custmer.Model.ListModal;
import com.mradking.mradkingshop.kirana_store.custmer.activity.Cart_list;
import com.mradking.mradkingshop.kirana_store.custmer.activity.More_product_Bt_list_main;
import com.mradking.mradkingshop.kirana_store.custmer.activity.product_list;
import com.mradking.mradkingshop.kirana_store.custmer.adapter.Home_page_product_adapter;
import com.mradking.mradkingshop.kirana_store.custmer.intface.RecyclerView_home_list;
import com.mradking.mradkingshop.kirana_store.custmer.utility.SlidingImage_Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class Home_fragment extends Fragment implements   View.OnClickListener {
    private static ViewPager mPager;
    private CircleIndicator indicator;
    private static FirebaseFirestore firebaseFirestore;
    private static FirebaseAuth firebaseAuth;
    public Home_page_product_adapter latestGovtJobRecyclerAdapter;
    LinearLayout aata,rice,oil,masalas,salt,sugar,dal,dry_fruits,besan;
    LinearLayout detergents,cleaners,dishwashers,repellents,air_frshners;
    LinearLayout hair_oil,shampoo,shoaps,toothpastes,skin_care,baby_care;
    LinearLayout tea,coffee,health_drinks,juices,soft_drinks;
    LinearLayout biscuits,namkeen,noodles,chocolates,ketchups,chips;
    CardView more_bt_1,more_bt_2,more_bt_3,more_bt_4,more_bt_5;
    static TextView total_amount_home;
    TextView view_cart_bt;
    TextView no_item_cart_tv;
    static LinearLayout cart_view_layout;
    String store_uid,key;

    static int count=0;

    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private String[] urls = new String[] {
            "https://via.placeholder.com/600/92c952",
            "https://via.placeholder.com/600/771796",
            "https://via.placeholder.com/600/24f355",
            "https://via.placeholder.com/600/d32776"
    };

    public  Home_fragment(String store_uid_1,String key1){
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        store_uid=store_uid_1;
        key=key1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.kirana_store_home, container, false);

        insilaise(root);

        init();




        item_click_handle();

        home_top_seller_list_setUp();

        return root;


    }

    private void home_top_seller_list_setUp() {


        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container_grocery, new Home_Page_Top_seller_List_fragment(store_uid, "kirana_product_grossery"));
        fragmentTransaction.add(R.id.container_home_hygiene_care, new Home_Page_Top_seller_List_fragment(store_uid, "kirana_home_hygience_product"));

        fragmentTransaction.add(R.id.container_persnoal_care_list, new Home_Page_Top_seller_List_fragment(store_uid, "kirana_persnoal_care_product"));
        fragmentTransaction.add(R.id.container_beverage_list, new Home_Page_Top_seller_List_fragment(store_uid, "kirana_beverages_product"));
        fragmentTransaction.add(R.id.container_snack_list, new Home_Page_Top_seller_List_fragment(store_uid, "kirana_snacks_product"));




        fragmentTransaction.commit();

    }



    private void item_click_handle() {


        view_cart_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(getActivity(), Cart_list.class);
                intent.putExtra("key",store_uid);
                startActivity(intent);
                getActivity().finish();
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        data_set_up();

        aata.setOnClickListener(this);
        rice.setOnClickListener(this);
        oil.setOnClickListener(this);
        masalas.setOnClickListener(this);
        salt.setOnClickListener(this);
        sugar.setOnClickListener(this);
        dal.setOnClickListener(this);
        dry_fruits.setOnClickListener(this);
        besan.setOnClickListener(this);


        detergents.setOnClickListener(this);
        cleaners.setOnClickListener(this);
        dishwashers.setOnClickListener(this);
        repellents.setOnClickListener(this);
        air_frshners.setOnClickListener(this);

        hair_oil.setOnClickListener(this);
        shampoo.setOnClickListener(this);
        shoaps.setOnClickListener(this);
        toothpastes.setOnClickListener(this);
        skin_care.setOnClickListener(this);
        baby_care.setOnClickListener(this);

        tea.setOnClickListener(this);
        coffee.setOnClickListener(this);
        health_drinks.setOnClickListener(this);
        juices.setOnClickListener(this);
        soft_drinks.setOnClickListener(this);


        biscuits.setOnClickListener(this);
        namkeen.setOnClickListener(this);
        noodles.setOnClickListener(this);
        chocolates.setOnClickListener(this);
        ketchups.setOnClickListener(this);
       chips.setOnClickListener(this);
       more_bt_1.setOnClickListener(this);
        more_bt_2.setOnClickListener(this);
        more_bt_3.setOnClickListener(this);
        more_bt_4.setOnClickListener(this);
        more_bt_5.setOnClickListener(this);
    }

    private void insilaise(View root) {
        mPager = (ViewPager) root.findViewById(R.id.view_pager);
        indicator = (CircleIndicator)root.findViewById(R.id.circle);
//
         total_amount_home=root.findViewById(R.id.total_home_amt);
        view_cart_bt=root.findViewById(R.id.view_cart_bt);
        no_item_cart_tv=root.findViewById(R.id.no_item_in_cart);

        cart_view_layout=root.findViewById(R.id.cart_view_layout);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        aata=root.findViewById(R.id.aata);
        rice=root.findViewById(R.id.rice);
        oil=root.findViewById(R.id.oil);
        masalas=root.findViewById(R.id.masalas);
        salt=root.findViewById(R.id.salt);
        sugar=root.findViewById(R.id.sugar);
        dal=root.findViewById(R.id.dal);
        dry_fruits=root.findViewById(R.id.dry_fruits);
        besan=root.findViewById(R.id.besan);


        detergents=root.findViewById(R.id.detergents);
        cleaners=root.findViewById(R.id.cleaners);
        dishwashers=root.findViewById(R.id.dishwashers);
        repellents=root.findViewById(R.id.repellents);
        air_frshners=root.findViewById(R.id.air_fresheners);



        hair_oil=root.findViewById(R.id.hair_oil);
        shampoo=root.findViewById(R.id.shampoo);
        shoaps=root.findViewById(R.id.shoaps);
        toothpastes=root.findViewById(R.id.toothpasters);
        skin_care=root.findViewById(R.id.skin_care);
        baby_care=root.findViewById(R.id.baby_care);


        tea=root.findViewById(R.id.tea);
        coffee=root.findViewById(R.id.coffee);
        health_drinks=root.findViewById(R.id.healthy_drinks);
        juices=root.findViewById(R.id.juices);
        soft_drinks=root.findViewById(R.id.soft_drink);


        biscuits=root.findViewById(R.id.biscuits);
        namkeen=root.findViewById(R.id.namkeen);
        noodles =root.findViewById(R.id.noodles);
        chocolates=root.findViewById(R.id.chocolate);
        ketchups=root.findViewById(R.id.kethups);
        chips=root.findViewById(R.id.chips);

        more_bt_1=root.findViewById(R.id.more_bt_1);
        more_bt_2=root.findViewById(R.id.more_bt_2);
        more_bt_3=root.findViewById(R.id.more_bt_3);
        more_bt_4=root.findViewById(R.id.more_bt_4);
        more_bt_5=root.findViewById(R.id.more_bt_5);

    }

//    private void home_page_slider(View root) {
//
//         ArrayList<String>image_list = null;
//         image_list.add("https://via.placeholder.com/600/92c952");
//         image_list.add("https://via.placeholder.com/600/24f355");
//        image_list.add("https://via.placeholder.com/600/92c952");
//        image_list.add("https://via.placeholder.com/600/24f355");
//
//
//        utilityX UtilityX=new utilityX(getActivity());
//        UtilityX.slider_maker(mPager,indicator,image_list,getActivity());
//
//    }
//

    @Override
    public void onClick(View view) {


        if (view == aata) {

            data_open("atta");

        }else if (view == rice) {
            data_open("rice");
        }else if (view == oil) {
            data_open("oil_ghee");
        }else if (view == masalas) {
            data_open("masalas");
        }else if (view == salt) {
            data_open("salt");
        }else if (view == sugar) {
            data_open("sugar");
        }else if (view == dal) {
            data_open("dal");
        }else if (view == dry_fruits) {
            data_open("dry_fruit");
        }else if (view == besan) {
            data_open("basan_sooji_daila");
        }

        else if (view == detergents) {
            data_open("detergents");
        }else if (view == cleaners) {
            data_open("cleners");
        }else if (view == dishwashers) {
            data_open("dishwhashers");
        }else if (view == repellents) {
            data_open("repellents");
        }else if (view == air_frshners) {
            data_open("air_freshners");
        }

        else if (view == hair_oil) {
            data_open("hair_oil");
        }else if (view == shampoo) {
            data_open("shampoo");
        }else if (view == shoaps) {
            data_open("soaps");
        }else if (view == toothpastes) {
            data_open("toothpastes");
        }else if (view == skin_care) {
            data_open("skins_care");
        }else if (view == baby_care) {
            data_open("baby_care");
        }


        else if (view == tea) {
            data_open("tea");
        }else if (view == coffee) {
            data_open("coffee");
        }else if (view == health_drinks) {
            data_open("heath_drink");
        }else if (view == juices) {
            data_open("juices");
        }else if (view == soft_drinks) {
            data_open("soft_drink");
        }


        else if (view == biscuits) {
            data_open("biscuits");
        }else if (view == namkeen) {
            data_open("namkeens");
        }else if (view == noodles) {
            data_open("nodles_pasta");
        }else if (view == chocolates) {
            data_open("chocolates");
        }else if (view == ketchups) {
            data_open("ketchups");
        }else if (view == chips) {
            data_open("chips");
        }else if(view== more_bt_1){

            data_open_for_more("kirana_product_grossery");
        }else if(view== more_bt_2){

            data_open_for_more("kirana_home_hygience_product");
        }
        else if(view== more_bt_3){

            data_open_for_more("kirana_persnoal_care_product");
        }
        else if(view== more_bt_4){

            data_open_for_more("kirana_beverages_product");
        }else if(view== more_bt_5){

            data_open_for_more("kirana_snacks_product");
        }




    }

    private void data_open_for_more(String key) {

        Intent intent=new Intent(getActivity(), More_product_Bt_list_main.class);
        intent.putExtra("key",key);
        intent.putExtra("store_uid",store_uid);
        startActivity(intent);

    }

    private void data_open(String atta) {

        Intent intent=new Intent(getActivity(), product_list.class);
        intent.putExtra("key",atta);
        intent.putExtra("store_uid",store_uid);
        startActivity(intent);


    }

    private  static void data_set_up() {


        Query firstQuery = firebaseFirestore.collection("kirna_Add_cart")
                .document(firebaseAuth.getUid().toString()).collection("cart")
                .whereEqualTo("order_status", "cart").limit(10);

        firstQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                QuerySnapshot documentSnapshots=task.getResult();
                int total_sale=0;
                for(int i = 0; i < documentSnapshots.getDocuments().size(); i++){


                    total_sale +=  Integer.parseInt((String) documentSnapshots.getDocuments().get(i).get("total_amount"));

                }


                if(total_sale > 0){


                    cart_view_layout.setVisibility(View.VISIBLE);
                    total_amount_home.setText("₹"+String.valueOf(total_sale));

                }else {

                    cart_view_layout.setVisibility(View.GONE);
                    total_amount_home.setText(String.valueOf(total_sale));

                }



            }
        });

    }


    private void init() {


        mPager.setAdapter(new SlidingImage_Adapter(getActivity(),urls));

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius


        NUM_PAGES = urls.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 6000, 6000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }



    public static class Home_Page_Top_seller_List_fragment  extends Fragment implements RecyclerView_home_list {
        private List<CommonModal> productList;
        private FirebaseFirestore firebaseFirestore;
        private FirebaseAuth firebaseAuth;
        public Home_page_product_adapter latestGovtJobRecyclerAdapter;
        ProgressDialog progressDialog;
        public DocumentSnapshot lastVisible;
        private Boolean isFirstPageFirstLoad = true;
        private RecyclerView grocery_recyler_view;

        String store_uid ,category;
        View root2;
        public Home_Page_Top_seller_List_fragment(String store_id,String category_id){

            store_uid=store_id;
            category=category_id;
        }


        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View root = inflater.inflate(R.layout.kirana_store_top_seller_list, container, false);
            root2 = inflater.inflate(R.layout.kirana_store_home, container, false);


            list_set_up(root);

            return  root;

        }





        private void list_set_up(View root) {

            firebaseFirestore=FirebaseFirestore.getInstance();
            firebaseAuth=FirebaseAuth.getInstance();
            productList = new ArrayList<CommonModal>();
            grocery_recyler_view = root.findViewById(R.id.grocery);
            Query query=    firebaseFirestore.collection("kirana_shopkeeper_product")
                    .document(store_uid)
                    .collection("proudcts")
                    .whereEqualTo("main_category",category);

            latestGovtJobRecyclerAdapter = new Home_page_product_adapter(getActivity(), productList,this);


            ListModal listModal = new ListModal();
            listModal.get_list( "no", true,query, productList, grocery_recyler_view, latestGovtJobRecyclerAdapter, getActivity());



        }



        @Override
        public void total_amount(int total_amt) {

            data_set();


        }

        private void data_set() {
            Query firstQuery = firebaseFirestore.collection("kirna_Add_cart")
                    .document(firebaseAuth.getUid().toString()).collection("cart")
                    .whereEqualTo("order_status", "cart").limit(10);

            firstQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    QuerySnapshot documentSnapshots=task.getResult();
                    int total_sale=0;
                    for(int i = 0; i < documentSnapshots.getDocuments().size(); i++){


                        total_sale +=  Integer.parseInt((String) documentSnapshots.getDocuments().get(i).get("total_amount"));

                    }

                    if(total_sale > 0){


                        cart_view_layout.setVisibility(View.VISIBLE);
                        total_amount_home.setText("₹"+String.valueOf(total_sale));

                    }else {

                        cart_view_layout.setVisibility(View.GONE);
                        total_amount_home.setText(String.valueOf(total_sale));

                    }





                }
            });




        }

        @Override
        public void visblity_cart_bar(int posion) {

        }

        @Override
        public void total_saving(int amt) {

        }

        @Override
        public void restart_act() {

        }
    }


}
