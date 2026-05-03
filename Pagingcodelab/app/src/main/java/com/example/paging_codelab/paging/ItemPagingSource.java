package com.example.paging_codelab.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.paging_codelab.model.Item;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class ItemPagingSource extends RxPagingSource<Integer, Item> {

    @NonNull
    @Override
    public Single<LoadResult<Integer, Item>> loadSingle(@NonNull LoadParams<Integer> params) {
        int page = params.getKey() != null ? params.getKey() : 1;

        try {
            List<Item> items = new ArrayList<>();

            // Generate dummy data
            for (int i = 0; i < 20; i++) {
                items.add(new Item("Item " + ((page - 1) * 20 + i)));
            }

            LoadResult<Integer, Item> result = new LoadResult.Page<>(
                    items,
                    page == 1 ? null : page - 1, // prevKey
                    page + 1                     // nextKey
            );
            return Single.just(result);

        } catch (Exception e) {
            return Single.just(new LoadResult.Error<>(e));
        }
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Item> state) {
        Integer anchorPosition = state.getAnchorPosition();
        if (anchorPosition == null) return null;

        LoadResult.Page<Integer, Item> anchorPage =
                state.closestPageToPosition(anchorPosition);

        if (anchorPage == null) return null;

        if (anchorPage.getPrevKey() != null) {
            return anchorPage.getPrevKey() + 1;
        } else if (anchorPage.getNextKey() != null) {
            return anchorPage.getNextKey() - 1;
        }

        return null;
    }
}
