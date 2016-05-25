package demo.service;

import demo.domain.AssetManifest;

public interface AssetManifestService {
    AssetManifest fetchAssetManifest();

    void invalidateCache();
}
