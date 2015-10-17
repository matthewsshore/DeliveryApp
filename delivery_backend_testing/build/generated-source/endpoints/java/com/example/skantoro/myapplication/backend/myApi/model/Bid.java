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
 * on 2015-10-17 at 00:34:18 UTC 
 * Modify at your own risk.
 */

package com.example.skantoro.myapplication.backend.myApi.model;

/**
 * Model definition for Bid.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the myApi. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Bid extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Double bid;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Double getBid() {
    return bid;
  }

  /**
   * @param bid bid or {@code null} for none
   */
  public Bid setBid(java.lang.Double bid) {
    this.bid = bid;
    return this;
  }

  @Override
  public Bid set(String fieldName, Object value) {
    return (Bid) super.set(fieldName, value);
  }

  @Override
  public Bid clone() {
    return (Bid) super.clone();
  }

}
