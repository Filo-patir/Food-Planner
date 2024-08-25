package filo.mamdouh.kershhelper.features.mainappfeatures.calendar.presenter;

import android.util.Log;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.contracts.CalendarContract;
import filo.mamdouh.kershhelper.datastorage.room.calendar.Calendar;
import filo.mamdouh.kershhelper.features.mainappfeatures.calendar.BaseCalendarAdapter;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarPresenter {
    private Repostiry repo;
    CalendarContract.View view;
    CompositeDisposable compositeDisposable;
    public CalendarPresenter( Repostiry repo, CalendarContract.View view){
        this.repo = repo;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }
    public void getCalendar(){

        compositeDisposable.add(
                Observable.fromCallable(() -> {
                    repo.getCalendar().subscribeOn(Schedulers.io()).subscribe(calendar -> Log.d("Filo", "AAAAAA: "+calendar.getMealID()));
                    ArrayList<MealsItem> mealsItems = new ArrayList<>();
                    repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isFriday()).subscribe(
                            onNext->{
                                Log.d("Filo", "HERE: "+onNext);
                                repo.getMealByID(onNext.getMealID()).subscribe(
                                        onNexts -> {
                                            Log.d("Filo", "BBBB: " + onNexts.getIdMeal());
                                            mealsItems.add(onNexts);
                                        }, throwable -> Log.d("Filo", "getCalendar: " + throwable.getMessage())
                                );}, throwable -> Log.d("Filo", "getCalendar: " + throwable.getMessage())
                            );
                    Log.d("Filo", "aaaa: "+mealsItems.size());
                    return mealsItems;
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        onNext -> view.updateUI(BaseCalendarAdapter.KEYS.get(6),onNext),throwable -> Log.d("Filo", "getCalendar: "+throwable.getMessage())
                ));
        compositeDisposable.add(
                Observable.fromCallable(() -> {
                    ArrayList<MealsItem> mealsItems = new ArrayList<>();
                    repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isSaturday()).subscribe(
                            onNext-> repo.getMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).subscribe(
                                    mealsItems::add
                            ),throwable -> Log.d("Filo", "getCalendar: "+throwable.getMessage())
                    );
                    return mealsItems;
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        onNext -> {
                            Log.d("Filo", "getCalendar: "+onNext);
                            view.updateUI(BaseCalendarAdapter.KEYS.get(0),onNext);},throwable -> Log.d("Filo", "getCalendar: "+throwable.getMessage())
                ));
        compositeDisposable.add(
                Observable.fromCallable(() -> {
                    ArrayList<MealsItem> mealsItems = new ArrayList<>();
                    repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isSunday()).subscribe(
                            onNext-> repo.getMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).subscribe(
                                    mealsItems::add
                            ),throwable -> Log.d("Filo", "getCalendar: "+throwable.getMessage())
                    );
                    return mealsItems;
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        onNext -> view.updateUI(BaseCalendarAdapter.KEYS.get(1),onNext),throwable -> Log.d("Filo", "getCalendar: "+throwable.getMessage())
                ));
        compositeDisposable.add(
                Observable.fromCallable(() -> {
                    ArrayList<MealsItem> mealsItems = new ArrayList<>();
                    repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isMonday()).subscribe(
                            onNext-> repo.getMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).subscribe(
                                    mealsItems::add
                            ),throwable -> Log.d("Filo", "getCalendar: "+throwable.getMessage())
                    );
                    return mealsItems;
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        onNext -> view.updateUI(BaseCalendarAdapter.KEYS.get(2),onNext),throwable -> Log.d("Filo", "getCalendar: "+throwable.getMessage())
                ));
        compositeDisposable.add(
                Observable.fromCallable(() -> {
                    ArrayList<MealsItem> mealsItems = new ArrayList<>();
                    repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isTuesday()).subscribe(
                            onNext-> repo.getMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).subscribe(
                                    mealsItems::add
                            ),throwable -> Log.d("Filo", "getCalendar: "+throwable.getMessage())
                    );
                    return mealsItems;
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        onNext -> view.updateUI(BaseCalendarAdapter.KEYS.get(3),onNext),throwable -> Log.d("Filo", "getCalendar: "+throwable.getMessage())
                ));
        compositeDisposable.add(
                Observable.fromCallable(() -> {
                    ArrayList<MealsItem> mealsItems = new ArrayList<>();
                    repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isWednesday()).subscribe(
                            onNext-> repo.getMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).subscribe(
                                    mealsItems::add
                            ),throwable -> Log.d("Filo", "getCalendar: "+throwable.getMessage())
                    );
                    return mealsItems;
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        onNext -> view.updateUI(BaseCalendarAdapter.KEYS.get(4),onNext),throwable -> Log.d("Filo", "getCalendar: "+throwable.getMessage())
                ));
        compositeDisposable.add(
                Observable.fromCallable(() -> {
                    ArrayList<MealsItem> mealsItems = new ArrayList<>();
                    repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isThursday()).subscribe(
                            onNext-> repo.getMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).subscribe(
                                    mealsItems::add
                            ),throwable -> Log.d("Filo", "getCalendar: "+throwable.getMessage())
                    );
                    return mealsItems;
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        onNext -> view.updateUI(BaseCalendarAdapter.KEYS.get(5),onNext),throwable -> Log.d("Filo", "getCalendar: "+throwable.getMessage())
                ));
    }
}
