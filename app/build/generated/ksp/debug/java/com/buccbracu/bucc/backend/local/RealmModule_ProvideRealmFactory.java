package com.buccbracu.bucc.backend.local;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.realm.kotlin.Realm;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class RealmModule_ProvideRealmFactory implements Factory<Realm> {
  @Override
  public Realm get() {
    return provideRealm();
  }

  public static RealmModule_ProvideRealmFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Realm provideRealm() {
    return Preconditions.checkNotNullFromProvides(RealmModule.INSTANCE.provideRealm());
  }

  private static final class InstanceHolder {
    private static final RealmModule_ProvideRealmFactory INSTANCE = new RealmModule_ProvideRealmFactory();
  }
}
