# Rx_java2_soussidev

A simple RxJava Lib contine (RxBus,RxAnimation,RxConnection and RxSharedPref)
<br>
[![](https://jitpack.io/v/datalink747/Rx_java2_soussidev.svg)](https://jitpack.io/#datalink747/Rx_java2_soussidev)
[![Ansible Role](https://img.shields.io/badge/Developer-Soussidev-yellow.svg)]()
[![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)]()

<a href='https://ko-fi.com/A243447K' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi4.png?v=0' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>

# Include:
[![Ansible Role](https://img.shields.io/badge/Rx-SharedPref-ff2c94.svg?style=flat-square)](https://github.com/datalink747/Rx_java2_soussidev/blob/master/app/src/main/java/com/soussidev/kotlin/rx_java2_soussidev/RxSharedpref_fragment.java)
[![Ansible Role](https://img.shields.io/badge/Rx-Bus-ff2c94.svg?style=flat-square)](https://github.com/datalink747/Rx_java2_soussidev/blob/master/app/src/main/java/com/soussidev/kotlin/rx_java2_soussidev/RxBusfragment.java)
[![Ansible Role](https://img.shields.io/badge/Rx-ActivityResult-ff2c94.svg?style=flat-square)](https://github.com/datalink747/Rx_java2_soussidev/blob/master/app/src/main/java/com/soussidev/kotlin/rx_java2_soussidev/RxActivityResult_fragment.java)
[![Ansible Role](https://img.shields.io/badge/Rx-Animation-ff2c94.svg?style=flat-square)](https://github.com/datalink747/Rx_java2_soussidev/blob/master/app/src/main/java/com/soussidev/kotlin/rx_java2_soussidev/Rxanimationfragment.java)
[![Ansible Role](https://img.shields.io/badge/Rx-Connection-ff2c94.svg?style=flat-square)](https://github.com/datalink747/Rx_java2_soussidev/blob/master/app/src/main/java/com/soussidev/kotlin/rx_java2_soussidev/RxConnectionfragment.java)
[![Ansible Role](https://img.shields.io/badge/Rx-Handler-ff2c94.svg?style=flat-square)]()
[![Ansible Role](https://img.shields.io/badge/Rx-RecyclerView-ff2c94.svg?style=flat-square)](https://github.com/datalink747/Rx_java2_soussidev/blob/master/app/src/main/java/com/soussidev/kotlin/rx_java2_soussidev/RxRecyclerviewfragment.java)
[![Ansible Role](https://img.shields.io/badge/Rx-Handler-ff2c94.svg?style=flat-square)](https://github.com/datalink747/Rx_java2_soussidev/blob/master/app/src/main/java/com/soussidev/kotlin/rx_java2_soussidev/RxHandlerfragment.java)
[![Ansible Role](https://img.shields.io/badge/Rx-Verify-ff2c94.svg?style=flat-square)](https://github.com/datalink747/Rx_java2_soussidev/blob/master/app/src/main/java/com/soussidev/kotlin/rx_java2_soussidev/RxVerifyfragment.java)
[![Ansible Role](https://img.shields.io/badge/Rx-TextInputLayout-ff2c94.svg?style=flat-square)](https://github.com/datalink747/Rx_java2_soussidev/blob/master/app/src/main/java/com/soussidev/kotlin/rx_java2_soussidev/RxVerifyfragment.java)

[![Ansible Role](https://img.shields.io/badge/Rx-AlertDialog-ff0044.svg?style=flat-square)]()


# Preview :
<table>
<tr>
<td>
<p>RxSharedPref </p>
<img src="picture/pref.png" height="450" width="280">
</td>
<td>
<p>RxBus </p>
<img src="picture/bus.png" height="450" width="280">
</td>
<td>
<p> RxAnimation </p>
<img src="picture/animation.png" height="450" width="280">
</td>
</tr>
<tr>

<td>
<p>RxConnection </p>
<img src="picture/connection.png" height="450" width="280">
</td>
</tr>
</table>

# Add dependencie to your project :

```gradle
allprojects {
repositories {
    jcenter()
    maven { url "https://jitpack.io" }
}
}

dependencies {
    compile 'com.github.datalink747:Rx_java2_soussidev:1.5.3'
}
```
# Code :
> RxPref
```java
public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rxpref=(TextView)view.findViewById(R.id.rxpref);



        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        final RxSharedPreferences_java rxShared = RxSharedPreferences_java.with(sharedPreferences);

        rxShared.putString("dev", "Soussi")
                .flatMap( name-> rxShared.putString("name", "soussi"))
                .flatMap(email -> rxShared.putString("email", "soussi.mohamed747@gmail.com"))
                .flatMap(phone -> rxShared.putInteger("tel", 55055055))
                .flatMap(post -> rxShared.putString("post", "dev android"))

                .flatMap(post -> rxShared.getAll())
                .flatMap(stringMap -> Observable.fromIterable(stringMap.entrySet()))
                .map(Object::toString)

               // .subscribe(s -> Log.d("TAG 1", s)
                .subscribe(s -> textlog1="Subscribe :"+s+"\n"
                );

        rxShared.getInt("tel", 0)
                .subscribe(tel -> {
                    //display phone
                    Log.d("TAG 2", "phone: " + tel);
                    textlog2="phone :"+tel+"\n";
                });

        Observable.just(new Admin())
                .flatMap(user -> rxShared.getInt("id", 0), (admin, id) -> {
                    admin.setId(id);
                    return admin;
                })
                .flatMap(admin -> rxShared.getString("name", ""), (admin, name) -> {
                    admin.setName(name);
                    return admin;
                })

                .flatMap(admin -> rxShared.getString("post", ""), (admin, post) -> {
                    admin.setPost(post);
                    return admin;
                })

                .flatMap(admin -> rxShared.getString("email", ""), (admin, email) -> {
                    admin.setEmail(email);
                    return admin;
                })

                .flatMap(admin -> rxShared.getInt("tel", 0), (admin, phone) -> {
                    admin.setTel(phone);
                    return admin;
                })

                .subscribe(admin -> {
                    //display admin
                    Log.d("TAG 3", admin.toString());
                    Log.d("TAG 4", admin.getEmail());
                    Log.d("TAG 5", admin.getTel().toString());

                    textlog3="admin :"+admin.toString()+"\n";
                    textlog4="admin email :"+admin.getEmail()+"\n";
                    textlog5="admin phone :"+admin.getTel().toString()+"\n";
                    textlog6="admin post :"+admin.getPost().toString()+"\n";

                });

        rxpref.setText(get_data());
    }
    public String get_data()
    {
        sb =new StringBuilder();
        sb.append(textlog1);
        sb.append("\n");
        sb.append(textlog2);
        sb.append("\n");
        sb.append(textlog3);
        sb.append("\n");
        sb.append(textlog4);
        sb.append("\n");
        sb.append(textlog5);
        sb.append("\n");
        sb.append(textlog6);
        return sb.toString();
    }

```
> RxConnection
```java
 @Override
    public void onResume() {
        super.onResume();

        networkDisposable = ReactiveNetwork.observeNetworkConnectivity(getActivity())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(connectivity -> {
                    Log.d(TAG, connectivity.toString());
                    final NetworkInfo.State state = connectivity.getState();
                    final String name = connectivity.getTypeName();
                    tvConnectivityStatus.setText(String.format("state: %s, typeName: %s", state, name));
                });

        internetDisposable = ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(isConnected -> tvInternetStatus.setText(isConnected.toString()));
    }

    @Override
    public void onPause() {
        super.onPause();
        safelyDispose(networkDisposable, internetDisposable);
    }

    private void safelyDispose(Disposable... disposables) {
        for (Disposable subscription : disposables) {
            if (subscription != null && !subscription.isDisposed()) {
                subscription.dispose();
            }
        }
    }
```
> RxActivityResult
```java
 private void getResultActivity()
    {
        final Intent intent = new Intent(getActivity(), Result.class);

        RxActivityResultCompact.startActivityForResult(this, intent, REQUEST_CODE)
                .subscribe(new Consumer<ActivityResult>() {
                    @Override
                    public void accept(@NonNull ActivityResult result) throws Exception {
                        if (result.isOk()) {
                            final String txt = result.getData().getStringExtra(Result.GET_TEXT);
                            textresult.setText(txt);
                        }
                    }
                });

    }
```
* Activity: Result.class
+ Send result
```java
private Intent data = new Intent();

data.putExtra(GET_TEXT, "Service is Disable");
setResult(Activity.RESULT_OK, data);
```

> RxVerify

* Methode 1 :
```java

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edit_email=(EditText)findViewById(R.id.edit_email);
        
         //RxVerify Your E-mail and Ckeck Validate Domain Name
        RxVerify.createFor(edit_email)
                .nonEmpty()
                .email()
                .with(new VerifyEmailDomain())
                .onValueChanged()
                .toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxVerifyResult<EditText>>() {
                    @Override public void call(RxVerifyResult<EditText> result) {
                        result.getItem().setError(result.isProper() ? null : result.getMessage());
                        Log.i(TAG, "VerifyCompac result " + result.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override public void call(Throwable throwable) {
                        Log.e(TAG, "VerifyCompac error", throwable);
                    }
                });
                
                }
```
* Methode 2 :
> Using support-v7
# RxTextInputLayout

```java
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edit_email_compac=(AppCompatEditText)view.findViewById(R.id.edit_email_compac);
        inputemail_compac=(TextInputLayout)view.findViewById(R.id.input_email_compac);
        
        RxTextInputLayout.createFor(edit_email_compac,inputemail_compac).RxVerifyCustom(Patterns.EMAIL_ADDRESS,"Incorect E-mail !!");
         
         }
        
        
```

# SDK Required
+ Target sdk:<br>
[![API](https://img.shields.io/badge/API-23%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=23)
+ Min sdk:<br>
[![API](https://img.shields.io/badge/API-19%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=19)

# Social Media
<table style="border:0px;">
   <tr>
      <td>
<a href="https://www.linkedin.com/in/soussimohamed/">
<img src="picture/linkedin.png" height="100" width="100" alt="Soussi Mohamed">
</a>
      </td>
      <td>
         <a href="https://twitter.com/soussimohamed7/">
<img src="picture/Twitter.png" height="60" width="60" alt="Soussi Mohamed">
</a>
     </td>
       <td>
         <a href="https://plus.google.com/u/0/+SoussiMohamed747">
<img src="picture/googleplus.png" height="60" width="60" alt="Soussi Mohamed">
</a>
     </td>
  </tr> 
</table>  

# Licence
```
Copyright 2017 Soussidev, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
