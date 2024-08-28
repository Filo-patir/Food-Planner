package filo.mamdouh.kershhelper.features.mainappfeatures.calendar.presenter;

import android.util.Log;

import java.util.ArrayList;

import filo.mamdouh.kershhelper.contracts.CalendarContract;
import filo.mamdouh.kershhelper.features.mainappfeatures.calendar.BaseCalendarAdapter;
import filo.mamdouh.kershhelper.models.MealsItem;
import filo.mamdouh.kershhelper.models.Repostiry;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CalendarPresenter {
    CalendarContract.View view;
    CompositeDisposable compositeDisposable;
    private Repostiry repo;

    public CalendarPresenter(Repostiry repo, CalendarContract.View view) {
        this.repo = repo;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }

    public void getCalendar() {
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isFriday()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> {
                    Observable.fromCallable(() -> {
                                ArrayList<MealsItem> mealsItems = new ArrayList<>();
                                repo.getSavedMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                        mealsItem -> mealsItems.add(mealsItem));
                                return mealsItems;
                            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(mealsItems -> {
                                Log.d("Filo", "Are you sure: " + mealsItems.size());
                                view.updateUI(BaseCalendarAdapter.KEYS.get(6), mealsItems);
                            });
                }
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isSaturday()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> {
                    Observable.fromCallable(() -> {
                                ArrayList<MealsItem> mealsItems = new ArrayList<>();
                                repo.getSavedMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                        mealsItem -> mealsItems.add(mealsItem));
                                return mealsItems;
                            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(mealsItems -> {
                                Log.d("Filo", "Are you sure: " + mealsItems.size());
                                view.updateUI(BaseCalendarAdapter.KEYS.get(0), mealsItems);
                            });
                }
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isSunday()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> {
                    Observable.fromCallable(() -> {
                                ArrayList<MealsItem> mealsItems = new ArrayList<>();
                                repo.getSavedMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                        mealsItem -> mealsItems.add(mealsItem));
                                return mealsItems;
                            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(mealsItems -> {
                                Log.d("Filo", "Are you sure: " + mealsItems.size());
                                view.updateUI(BaseCalendarAdapter.KEYS.get(1), mealsItems);
                            });
                }
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isMonday()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> {
                    Observable.fromCallable(() -> {
                                ArrayList<MealsItem> mealsItems = new ArrayList<>();
                                repo.getSavedMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                        mealsItem -> mealsItems.add(mealsItem));
                                return mealsItems;
                            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(mealsItems -> {
                                Log.d("Filo", "Are you sure: " + mealsItems.size());
                                view.updateUI(BaseCalendarAdapter.KEYS.get(2), mealsItems);
                            });
                }
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isTuesday()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> {
                    Observable.fromCallable(() -> {
                                ArrayList<MealsItem> mealsItems = new ArrayList<>();
                                repo.getSavedMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                        mealsItem -> mealsItems.add(mealsItem));
                                return mealsItems;
                            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(mealsItems -> {
                                Log.d("Filo", "Are you sure: " + mealsItems.size());
                                view.updateUI(BaseCalendarAdapter.KEYS.get(3), mealsItems);
                            });
                }
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isWednesday()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> {
                    Observable.fromCallable(() -> {
                                ArrayList<MealsItem> mealsItems = new ArrayList<>();
                                repo.getSavedMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                        mealsItem -> mealsItems.add(mealsItem));
                                return mealsItems;
                            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(mealsItems -> {
                                Log.d("Filo", "Are you sure: " + mealsItems.size());
                                view.updateUI(BaseCalendarAdapter.KEYS.get(4), mealsItems);
                            });
                }
        ));
        compositeDisposable.add(repo.getCalendar().subscribeOn(Schedulers.io()).filter(calendar -> calendar.isThursday()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                onNext -> {
                    Observable.fromCallable(() -> {
                                ArrayList<MealsItem> mealsItems = new ArrayList<>();
                                repo.getSavedMealByID(onNext.getMealID()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                        mealsItem -> mealsItems.add(mealsItem));
                                return mealsItems;
                            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                            .subscribe(mealsItems -> {
                                Log.d("Filo", "Are you sure: " + mealsItems.size());
                                view.updateUI(BaseCalendarAdapter.KEYS.get(5), mealsItems);
                            });
                }
        ));
    }

    public void removeItem(String day, MealsItem mealsItem) {
        compositeDisposable.clear();
        repo.getCalendarByITD(mealsItem.getIdMeal()).subscribeOn(Schedulers.io()).subscribe(
                onNext -> {
                    switch (day) {
                        case "Sunday":
                            onNext.setSunday(false);
                            repo.addToCalendar(onNext).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
                            break;
                        case "Monday":
                            onNext.setMonday(false);
                            repo.addToCalendar(onNext).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
                            break;
                        case "Tuesday":
                            onNext.setTuesday(false);
                            repo.addToCalendar(onNext).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
                            break;
                        case "Wednesday":
                            onNext.setWednesday(false);
                            repo.addToCalendar(onNext).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
                            break;
                        case "Thursday":
                            onNext.setThursday(false);
                            repo.addToCalendar(onNext).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
                            break;
                        case "Friday":
                            onNext.setFriday(false);
                            repo.addToCalendar(onNext).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
                            break;
                    }
                }
        );
    }
}
