package edu.orangecoastcollege.cs273.wheretonext;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.List;

public class CollegeListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<College> collegesList;
    private CollegeListAdapter collegesListAdapter;
    private ListView collegesListView;

    private EditText nameEditText;
    private EditText populationEditText;
    private EditText tuitionEditText;
    private RatingBar collegeRatingBar;

    private ListView collegeListView;
    private CollegeListAdapter mCollegeListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_list);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        populationEditText = (EditText) findViewById(R.id.populationEditText);
        tuitionEditText = (EditText) findViewById(R.id.tuitionEditText);
        collegeRatingBar = (RatingBar) findViewById(R.id.collegeRatingBar);
        collegeListView = (ListView) findViewById(R.id.collegeListView);

        //this.deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);

        // TODO: Comment this section out once the colleges below have been added to the database,
        // TODO: so they are not added multiple times (prevent duplicate entries)
        db.addCollege(new College("UC Berkeley", 42082, 14068, 4.7, "ucb.png"));
        db.addCollege(new College("UC Irvine", 31551, 15026.47, 4.3, "uci.png"));
        db.addCollege(new College("UC Los Angeles", 43301, 25308, 4.5, "ucla.png"));
        db.addCollege(new College("UC San Diego", 33735, 20212, 4.4, "ucsd.png"));
        db.addCollege(new College("CSU Fullerton", 38948, 6437, 4.5, "csuf.png"));
        db.addCollege(new College("CSU Long Beach", 37430, 6452, 4.4, "csulb.png"));

        // TODO:  Fill the collegesList with all Colleges from the database
        collegesList = db.getAllColleges();
        // TODO:  Connect the list adapter with the list
        mCollegeListAdapter = new CollegeListAdapter(this, R.layout.college_list_item, collegesList);
        // TODO:  Set the list view to use the list adapter
        collegeListView.setAdapter(mCollegeListAdapter);
    }

    public void viewCollegeDetails(View view) {

        // TODO: Implement the view college details using an Intent
        Intent intent = new Intent(this, CollegeDetailsActivity.class);

        College selectedCollege = (College)view.getTag();

        intent.putExtra("Name", selectedCollege.getName());
        intent.putExtra("Population", selectedCollege.getPopulation());
        intent.putExtra("Tuition", selectedCollege.getTuition());
        intent.putExtra("Rating", selectedCollege.getRating());
        intent.putExtra("ImageName", selectedCollege.getImageName());
        startActivity(intent);

    }

    public void addCollege(View view) {

        // TODO: Implement the code for when the user clicks on the addCollegeButton
        College newCollege = new College(nameEditText.getText().toString(),
                Integer.parseInt(populationEditText.getText().toString()),
                Double.parseDouble(tuitionEditText.getText().toString()),
                collegeRatingBar.getRating());
        db.addCollege(newCollege);
        collegesList.add(newCollege);
        mCollegeListAdapter.notifyDataSetChanged();
    }

}
