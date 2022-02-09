package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static int i = 0 ;
    private  TextView textViewId ;
    private TextView textViewTitle;
    private  TextView textViewBody ;
    private  Button btnNext;
    private  Button btnPrevious;
    private  Post[] arrPosts ;
    private RecyclerView commentRecyclerView;
    private Comment[] arrComments ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewId = findViewById(R.id.textView_id);
        textViewTitle =findViewById(R.id.textView_title);
        textViewBody = findViewById(R.id.textView_body);
        btnNext = findViewById(R.id.btn_next);
        btnPrevious = findViewById(R.id.btn_previous);

        commentRecyclerView = findViewById(R.id.comment_recyclerview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL,false);
        commentRecyclerView.setLayoutManager(layoutManager);
        final CommentListAdaptor commentListAdaptor = new CommentListAdaptor();
        commentRecyclerView.setAdapter(commentListAdaptor);



        ApiInterface apiInterface =  ApiClinet.getClient().create(ApiInterface.class);

        Call<Post[]> call = apiInterface.getPosts();
        // call.execute build connection in main Thread
        // call.enqueue in background thread instead to main thread
        call.enqueue(new Callback<Post[]>() {
            @Override
            public void onResponse(Call<Post[]> call, Response<Post[]> response) {
                if(response.isSuccessful())
                    arrPosts = response.body();
                if( arrPosts != null) {


                    textViewId.setText(arrPosts[i].getId() + "");
                    textViewTitle.setText(arrPosts[i].getTitle());
                    textViewBody.setText(arrPosts[i].getBody());

                    btnNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (i < arrPosts.length) {
                                i++;
                                textViewId.setText(arrPosts[i].getId() + "");
                                textViewTitle.setText(arrPosts[i].getTitle());
                                textViewBody.setText(arrPosts[i].getBody());
                                getComments( i , commentListAdaptor );

                            } else {
                                Toast.makeText(getApplicationContext(), "This is the last post", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    btnPrevious.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (i > 0) {
                                i--;
                                textViewId.setText(arrPosts[i].getId() + "");
                                textViewTitle.setText(arrPosts[i].getTitle());
                                textViewBody.setText(arrPosts[i].getBody());
                                 getComments( i , commentListAdaptor );

                                } else {
                                Toast.makeText(getApplicationContext(), "This is the first post", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });






                }
            }

            @Override
            public void onFailure(Call<Post[]> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });




    }
    public void getComments( int postId ,CommentListAdaptor commentListAdaptor ){
        // Comments
        ApiInterface apiInterfaceComment = ApiClinet.getClientComment(arrPosts[postId].getId()).create(ApiInterface.class);

        Call<Comment[]> commentCall = apiInterfaceComment.getComment();
        commentCall.enqueue(new Callback<Comment[]>() {
            @Override
            public void onResponse(Call<Comment[]> call, Response<Comment[]> response) {
                if(response.isSuccessful()){
                    arrComments = response.body();
                }

                if( arrComments!= null){
                    commentListAdaptor.setCommentsArr(Arrays.asList(arrComments));
                    commentListAdaptor.notifyDataSetChanged();


                }
            }

            @Override
            public void onFailure(Call<Comment[]> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}