package com.manoj.rnm.universe.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.manoj.rnm.universe.api.CharacterApi
import com.manoj.rnm.universe.api.CharacterMediator
import com.manoj.rnm.universe.core.AppConstants
import com.manoj.rnm.universe.local.CharacterDatabase
import com.manoj.rnm.universe.local.CharacterEntity
import com.manoj.rnm.universe.repo.CharactersDataRepository
import com.manoj.rnm.universe.repo.CharactersDataRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideCharacterDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            "character.db",
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_API_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
        return retrofitBuilder.build()
    }

    @Singleton
    @Provides
    fun provideCharacterApi(retrofit: Retrofit): CharacterApi = retrofit.create(CharacterApi::class.java)

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideCharacterPager(
        characterDatabase: CharacterDatabase,
        characterApi: CharacterApi,
    ): Pager<Int, CharacterEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = CharacterMediator(
                pokemonDatabase = characterDatabase,
                pokemonApi = characterApi,
            ),
            pagingSourceFactory = {
                characterDatabase.characterDao.pagingSource()
            },
        )
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(
        characterPager: Pager<Int, CharacterEntity>
    ): CharactersDataRepository {
        return CharactersDataRepositoryImpl(
            characterPager = characterPager
        )
    }
}