package com.example.rxdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "myApp";
    private Observable<Student> myObservable;
    private DisposableObserver<Student> myObserver;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myObservable = Observable.create((emitter) -> {
            ArrayList<Student> studentArrayList = getStudents();

            for(Student student:studentArrayList) {
                emitter.onNext(student);
            }

            emitter.onComplete();
        });

        compositeDisposable.add(
                myObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
//                        .map(new Function<Student, Student>() {
//                            @Override
//                            public Student apply(Student student) throws Exception {
//                                student.setName(student.getName().toUpperCase());
//                                return student;
//                            }
//                        })
                        .flatMap(new Function<Student, Observable<Student>>() {
                            @Override
                            public Observable<Student> apply(Student student) throws Exception {

                                Student student1 = new Student();
                                student1.setName(student.getName());

                                Student student2 = new Student();
                                student2.setName("Now Member " + student.getName());

                                student.setName(student.getName().toUpperCase());
                                return Observable.just(student, student1, student2);
                            }
                        })
                        .subscribeWith(getObserver())
        );

    }

    private DisposableObserver getObserver() {
        myObserver = new DisposableObserver<Student>() {
            @Override
            public void onNext(Student s) {
                Log.i(TAG, "onNext invoked with " + s.getName());
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
        student2.setName("name2");
        student2.setEmail("name1@hugahuga");
        student2.setAge(29);
        students.add(student2);

        Student student3 = new Student();
        student3.setName("name3");
        student3.setEmail("name1@hugahuga");
        student3.setAge(29);
        students.add(student3);

        Student student4 = new Student();
        student4.setName("name4");
        student4.setEmail("name1@hugahuga");
        student4.setAge(29);
        students.add(student4);

        Student student5 = new Student();
        student5.setName("name5");
        student5.setEmail("name1@hugahuga");
        student5.setAge(29);
        students.add(student5);

        return students;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}