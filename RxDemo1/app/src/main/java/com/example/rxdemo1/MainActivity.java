package com.example.rxdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "myApp";
    private Observable<Student> myObservable;
    private DisposableObserver<Student> myObserver;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
//    private String[] greetings = {"Hello A", "Hello B", "Hello C"};
//    private Integer[] nums = {1,2,3,4,5};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myObservable = Observable.create(new ObservableOnSubscribe<Student>() {
            @Override
            public void subscribe(ObservableEmitter<Student> emitter) throws Exception {

                ArrayList<Student> studentArrayList = getStudents();

                for(Student student:studentArrayList) {
                    emitter.onNext(student);
                }

                emitter.onComplete();
            }
        });

        compositeDisposable.add(
                myObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(getObserver())
        );

    }

    private DisposableObserver getObserver() {
        myObserver = new DisposableObserver<Student>() {
            @Override
            public void onNext(Student s) {
                Log.i(TAG, "onNext invoked" + s.getEmail());
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError invoked");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete invoked");
            }
        };

        return myObserver;
    }

    private ArrayList<Student> getStudents() {

        ArrayList<Student> students = new ArrayList<>();

        Student student1 = new Student();
        student1.setName("name1");
        student1.setEmail("name1@hugahuga");
        student1.setAge(29);
        students.add(student1);

        Student student2 = new Student();
        student1.setName("name2");
        student1.setEmail("name1@hugahuga");
        student1.setAge(29);
        students.add(student2);

        Student student3 = new Student();
        student1.setName("name3");
        student1.setEmail("name1@hugahuga");
        student1.setAge(29);
        students.add(student3);

        Student student4 = new Student();
        student1.setName("name4");
        student1.setEmail("name1@hugahuga");
        student1.setAge(29);
        students.add(student4);

        Student student5 = new Student();
        student1.setName("name5");
        student1.setEmail("name1@hugahuga");
        student1.setAge(29);
        students.add(student5);

        return students;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}