package com.hipay.hipayfullservice.core.models;

import com.hipay.hipayfullservice.core.mapper.AbstractMapper;
import com.hipay.hipayfullservice.core.mapper.interfaces.MapBehaviour;

/**
 * Created by nfillion on 25/01/16.
 */
public class FraudScreening {

    protected Integer scoring;
    protected FraudScreeningResult result;
    protected FraudScreeningReview review;

    public FraudScreening() {
    }

    public enum FraudScreeningResult {

        FraudScreeningResultUnknown ("unknown"),
        FraudScreeningResultPending ("pending"),
        FraudScreeningResultAccepted ("accepted"),
        FraudScreeningResultBlocked ("blocked"),
        FraudScreeningResultChallenged ("challenged");

        protected final String result;

        FraudScreeningResult(String result) {
            this.result = result;
        }

        public String getStringValue() {
            return this.result;
        }

        public static FraudScreeningResult fromStringValue(String value) {

            if (value == null) return null;

            if (value.equalsIgnoreCase(FraudScreeningResultPending.getStringValue())) {
                return FraudScreeningResultPending;
            }

            if (value.equalsIgnoreCase(FraudScreeningResultAccepted.getStringValue())) {
                return FraudScreeningResultAccepted;
            }

            if (value.equalsIgnoreCase(FraudScreeningResultBlocked.getStringValue())) {
                return FraudScreeningResultBlocked;
            }

            if (value.equalsIgnoreCase(FraudScreeningResultChallenged.getStringValue())) {
                return FraudScreeningResultChallenged;
            }
            return null;
        }
    }

    public enum FraudScreeningReview {

        FraudScreeningReviewNone("none"),
        FraudScreeningReviewPending("pending"),
        FraudScreeningReviewAllowed("allowed"),
        FraudScreeningReviewDenied("denied");

        protected final String review;

        FraudScreeningReview(String review) {
            this.review = review;
        }

        public String getStringValue() {
            return this.review;
        }

        public static FraudScreeningReview fromStringValue(String value) {

            if (value == null) return null;

            if (value.equalsIgnoreCase(FraudScreeningReviewPending.getStringValue())) {
                return FraudScreeningReviewPending;
            }

            if (value.equalsIgnoreCase(FraudScreeningReviewAllowed.getStringValue())) {
                return FraudScreeningReviewAllowed;
            }

            if (value.equalsIgnoreCase(FraudScreeningReviewDenied.getStringValue())) {
                return FraudScreeningReviewDenied;
            }

            return null;
        }
    }

    public Integer getScoring() {
        return scoring;
    }

    public void setScoring(Integer scoring) {
        this.scoring = scoring;
    }

    public FraudScreeningResult getResult() {
        return result;
    }

    public void setResult(FraudScreeningResult result) {
        this.result = result;
    }

    public FraudScreeningReview getReview() {
        return review;
    }

    public void setReview(FraudScreeningReview review) {
        this.review = review;
    }

    public static class FraudScreeningMapper extends AbstractMapper {
        public FraudScreeningMapper() {
            //super();
        }

        @Override
        protected boolean isClassValid() {

            if (this.getBehaviour() instanceof MapBehaviour) {

                if (    this.getStringForKey("result") != null &&
                        this.getIntegerForKey("scoring") != null) {

                    return true;
                }
            }

            return false;
        }

        protected Object mappedObject() {

            FraudScreening object = new FraudScreening();

            object.setScoring(this.getIntegerForKey("scoring"));

            String resultString = this.getLowercaseStringForKey("result");
            FraudScreeningResult result = FraudScreeningResult.fromStringValue(resultString);
            if (result == null) {
                result = FraudScreeningResult.FraudScreeningResultUnknown;
            }
            object.setResult(result);

            String reviewString = this.getLowercaseStringForKey("review");
            FraudScreeningReview review = FraudScreeningReview.fromStringValue(reviewString);
            if (review == null) {
                review = FraudScreeningReview.FraudScreeningReviewNone;
            }
            object.setReview(review);

            return object;

        }

    }
}