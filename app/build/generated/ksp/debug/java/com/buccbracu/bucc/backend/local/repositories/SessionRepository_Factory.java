package com.buccbracu.bucc.backend.local.repositories;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.realm.kotlin.Realm;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class SessionRepository_Factory implements Factory<SessionRepository> {
  private final Provider<Realm> realmProvider;

  public SessionRepository_Factory(Provider<Realm> realmProvider) {
    this.realmProvider = realmProvider;
  }

  @Override
  public SessionRepository get() {
    return newInstance(realmProvider.get());
  }

  public static SessionRepository_Factory create(Provider<Realm> realmProvider) {
    return new SessionRepository_Factory(realmProvider);
  }

  public static SessionRepository newInstance(Realm realm) {
    return new SessionRepository(realm);
  }
}
