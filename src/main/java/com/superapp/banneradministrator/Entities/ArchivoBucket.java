package com.superapp.banneradministrator.Entities;

import java.util.Date;

public class ArchivoBucket {

    private String bucketName;
    /** The key under which this object is stored */
    private String key;
    /** Hex encoded MD5 hash of this object's contents, as computed by Amazon S3 */
    private String eTag;
    /** The size of this object, in bytes */
    private long size;

    /** The date, according to Amazon S3, when this object was last modified */
    private Date lastModified;

    /** The class of storage used by Amazon S3 to store this object */
    private String storageClass;
    private String resourceUrl;

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getBucketName() {return bucketName;}
    public void setBucketName(String bucketName) {this.bucketName = bucketName;}
    public String getKey() {return key;}
    public void setKey(String key) {this.key = key;}
    public String geteTag() {return eTag;}
    public void seteTag(String eTag) {this.eTag = eTag;}
    public long getSize() {return size;}
    public void setSize(long size) {this.size = size;}
    public Date getLastModified() {return lastModified;}
    public void setLastModified(Date lastModified) {this.lastModified = lastModified;}
    public String getStorageClass() {return storageClass;}
    public void setStorageClass(String storageClass) {this.storageClass = storageClass;}
}
