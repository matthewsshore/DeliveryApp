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
 * on 2015-10-13 at 22:59:29 UTC 
 * Modify at your own risk.
 */

package com.example.skantoro.myapplication.backend.myApi.model;

/**
 * Model definition for Order.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the myApi. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Order extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer addressID;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double bid;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double estPrice;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer orderID;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String orderName;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer status;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer userID;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getAddressID() {
    return addressID;
  }

  /**
   * @param addressID addressID or {@code null} for none
   */
  public Order setAddressID(java.lang.Integer addressID) {
    this.addressID = addressID;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getBid() {
    return bid;
  }

  /**
   * @param bid bid or {@code null} for none
   */
  public Order setBid(java.lang.Double bid) {
    this.bid = bid;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getEstPrice() {
    return estPrice;
  }

  /**
   * @param estPrice estPrice or {@code null} for none
   */
  public Order setEstPrice(java.lang.Double estPrice) {
    this.estPrice = estPrice;
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
  public Order setOrderID(java.lang.Integer orderID) {
    this.orderID = orderID;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getOrderName() {
    return orderName;
  }

  /**
   * @param orderName orderName or {@code null} for none
   */
  public Order setOrderName(java.lang.String orderName) {
    this.orderName = orderName;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getStatus() {
    return status;
  }

  /**
   * @param status status or {@code null} for none
   */
  public Order setStatus(java.lang.Integer status) {
    this.status = status;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getUserID() {
    return userID;
  }

  /**
   * @param userID userID or {@code null} for none
   */
  public Order setUserID(java.lang.Integer userID) {
    this.userID = userID;
    return this;
  }

  @Override
  public Order set(String fieldName, Object value) {
    return (Order) super.set(fieldName, value);
  }

  @Override
  public Order clone() {
    return (Order) super.clone();
  }

}
