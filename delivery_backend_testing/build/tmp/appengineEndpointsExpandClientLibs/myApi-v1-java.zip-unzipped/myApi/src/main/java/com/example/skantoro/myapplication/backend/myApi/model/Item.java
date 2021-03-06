/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2015-08-03 17:34:38 UTC)
 * on 2015-10-02 at 20:09:31 UTC 
 * Modify at your own risk.
 */

package com.example.skantoro.myapplication.backend.myApi.model;

/**
 * Model definition for Item.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the myApi. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Item extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer itemID;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String itemName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer orderID;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String pictureLocation;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getItemID() {
    return itemID;
  }

  /**
   * @param itemID itemID or {@code null} for none
   */
  public Item setItemID(java.lang.Integer itemID) {
    this.itemID = itemID;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getItemName() {
    return itemName;
  }

  /**
   * @param itemName itemName or {@code null} for none
   */
  public Item setItemName(java.lang.String itemName) {
    this.itemName = itemName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getOrderID() {
    return orderID;
  }

  /**
   * @param orderID orderID or {@code null} for none
   */
  public Item setOrderID(java.lang.Integer orderID) {
    this.orderID = orderID;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getPictureLocation() {
    return pictureLocation;
  }

  /**
   * @param pictureLocation pictureLocation or {@code null} for none
   */
  public Item setPictureLocation(java.lang.String pictureLocation) {
    this.pictureLocation = pictureLocation;
    return this;
  }

  @Override
  public Item set(String fieldName, Object value) {
    return (Item) super.set(fieldName, value);
  }

  @Override
  public Item clone() {
    return (Item) super.clone();
  }

}
