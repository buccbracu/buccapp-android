package com.buccbracu.bucc.backend.local.viewmodels;

import com.buccbracu.bucc.backend.local.repositories.SessionRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class LoginVM_Factory implements Factory<LoginVM> {
  private final Provider<SessionRepository> sessionRProvider;

  public LoginVM_Factory(Provider<SessionRepository> sessionRProvider) {
    this.sessionRProvider = sessionRProvider;
  }

  @Override
  public LoginVM get() {
    return newInstance(sessionRProvider.get());
  }

  public static LoginVM_Factory create(Provider<SessionRepository> sessionRProvider) {
    return new LoginVM_Factory(sessionRProvider);
  }

  public static LoginVM newInstance(SessionRepository sessionR) {
    return new LoginVM(sessionR);
  }
}
