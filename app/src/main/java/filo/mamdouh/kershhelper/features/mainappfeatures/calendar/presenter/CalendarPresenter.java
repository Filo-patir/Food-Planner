package filo.mamdouh.kershhelper.features.mainappfeatures.calendar.presenter;

import android.util.Log;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.contracts.CalendarContract;
import filo.mamdouh.kershhelper.features.mainappfeatures.calendar.BaseCalendarAdapter;
import filo.mamdouh.kershhelper.models.Calendar;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarPresenter {
    CalendarContract.View view;
    CompositeDisposable compositeDisposable;
    private final Repostiry repo;

    public CalendarPresenter(Repostiry repo, CalendarContract.View view) {
        this.repo = repo;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    public void getCalendar() {
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(Calendar::isSaturday).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> compositeDisposable.add(Observable.fromCallable(() -> {
                            ArrayList<MealsItem> mealsItems = new ArrayList<>();
                            compositeDisposable.add(repo.getSavedMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                    mealsItems::add));
                            return mealsItems;
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mealsItems -> {
                            Log.d("Filo", "Are you sure: " + mealsItems.size());
                            view.updateUI(BaseCalendarAdapter.KEYS.get(0), mealsItems);
                        }))
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(Calendar::isSunday).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> compositeDisposable.add(Observable.fromCallable(() -> {
                            ArrayList<MealsItem> mealsItems = new ArrayList<>();
                            compositeDisposable.add(repo.getSavedMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                    mealsItems::add));
                            return mealsItems;
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mealsItems -> {
                            Log.d("Filo", "Are you sure: " + mealsItems.size());
                            view.updateUI(BaseCalendarAdapter.KEYS.get(1), mealsItems);
                        }))
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(Calendar::isMonday).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> compositeDisposable.add(Observable.fromCallable(() -> {
                            ArrayList<MealsItem> mealsItems = new ArrayList<>();
                            compositeDisposable.add(repo.getSavedMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                    mealsItems::add));
                            return mealsItems;
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mealsItems -> {
                            Log.d("Filo", "Are you sure: " + mealsItems.size());
                            view.updateUI(BaseCalendarAdapter.KEYS.get(2), mealsItems);
                        }))
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(Calendar::isTuesday).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> compositeDisposable.add(Observable.fromCallable(() -> {
                            ArrayList<MealsItem> mealsItems = new ArrayList<>();
                            compositeDisposable.add(repo.getSavedMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                    mealsItems::add));
                            return mealsItems;
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mealsItems -> {
                            Log.d("Filo", "Are you sure: " + mealsItems.size());
                            view.updateUI(BaseCalendarAdapter.KEYS.get(3), mealsItems);
                        }))
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(Calendar::isWednesday).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> compositeDisposable.add(Observable.fromCallable(() -> {
                            ArrayList<MealsItem> mealsItems = new ArrayList<>();
                            compositeDisposable.add(repo.getSavedMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                    mealsItems::add));
                            return mealsItems;
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mealsItems -> {
                            Log.d("Filo", "Are you sure: " + mealsItems.size());
                            view.updateUI(BaseCalendarAdapter.KEYS.get(4), mealsItems);
                        }))
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(Calendar::isThursday).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> compositeDisposable.add(Observable.fromCallable(() -> {
                            ArrayList<MealsItem> mealsItems = new ArrayList<>();
                            compositeDisposable.add(repo.getSavedMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                    mealsItems::add));
                            return mealsItems;
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mealsItems -> {
                            Log.d("Filo", "Are you sure: " + mealsItems.size());
                            view.updateUI(BaseCalendarAdapter.KEYS.get(5), mealsItems);
                        }))
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(Calendar::isFriday).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> compositeDisposable.add(Observable.fromCallable(() -> {
                            Log.d("Filo", "getCalendar:  31 mealID: " + onNext.getMealID());
                            ArrayList<MealsItem> mealsItems = new ArrayList<>();
                            compositeDisposable.add(repo.getSavedMealByID(onNext.getMealID()).subscribe(
                                    meal -> {
                                        Log.d("Filo", "getCalendar:  aaaaaaaaa" + meal);
                                        mealsItems.add(meal);
                                    }, e -> Log.d("Filo", "getCalendar:  error " + e.getMessage())));
                            return mealsItems;
                        }).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mealsItems -> {
                            Log.d("Filo", "Are you sure: " + mealsItems.size());
                            view.updateUI(BaseCalendarAdapter.KEYS.get(6), mealsItems);
                        })), onError -> Log.d("Filo", "getCalendar:  error " + onError.getMessage())
        ));
    }

    public void removeItem(String day, MealsItem mealsItem) {
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(repo.getCalendarByITD(mealsItem.getIdMeal()).subscribeOn(Schedulers.io()).subscribe(
                onNext -> {
                    switch (day) {
                        case "Sunday":
                            onNext.setSunday(false);
                            disposable.add(repo.addToCalendar(onNext).subscribeOn(Schedulers.io()).subscribe());
                            break;
                        case "Monday":
                            onNext.setMonday(false);
                            disposable.add(repo.addToCalendar(onNext).subscribeOn(Schedulers.io()).subscribe());
                            break;
                        case "Tuesday":
                            onNext.setTuesday(false);
                            disposable.add(repo.addToCalendar(onNext).subscribeOn(Schedulers.io()).subscribe());
                            break;
                        case "Wednesday":
                            onNext.setWednesday(false);
                            disposable.add(repo.addToCalendar(onNext).subscribeOn(Schedulers.io()).subscribe());
                            break;
                        case "Thursday":
                            onNext.setThursday(false);
                            disposable.add(repo.addToCalendar(onNext).subscribeOn(Schedulers.io()).subscribe());
                            break;
                        case "Friday":
                            onNext.setFriday(false);
                            disposable.add(repo.addToCalendar(onNext).subscribeOn(Schedulers.io()).subscribe());
                            break;
                    }
                },
                throwable -> Log.d("Filo", "Error: " + throwable.getMessage()), ()->{
                    disposable.clear();
                    compositeDisposable.clear();
                }
        ));
    }

    public void clearSubscriptions()
    {
        compositeDisposable.clear();
    }
}
