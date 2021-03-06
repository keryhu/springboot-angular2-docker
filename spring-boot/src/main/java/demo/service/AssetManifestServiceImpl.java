package demo.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import demo.domain.AssetManifest;
import demo.domain.FrontUrl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Service("assetManifestService")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssetManifestServiceImpl implements AssetManifestService {

    private final FrontUrl frontUrl;


    @Cacheable("assetManifest")
    public AssetManifest fetchAssetManifest() {
        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(frontUrl.getAssetManifestUrl())
                .build();
        try {
            final Response response = client.newCall(request).execute();
            final String jsonStr = response.body().string();
            return AssetManifest.newInstance(jsonStr);
        } catch (IOException e) {
            log.info("manifest does not exist. fallback to default assets.");
            return new AssetManifest(); // fallback
        }
    }

    @CacheEvict("assetManifest")
    public void invalidateCache() {
        log.info("the cache of asset manifest was invalidated.");
    }
}
